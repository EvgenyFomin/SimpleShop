<#import "parts/common.ftl" as c>
<#import "parts/plusMinusInput.ftl" as pmi>
<#import "parts/pager.ftl" as p>

<@c.page>
    <div class="container p-3">
        <#assign
        key_list = productMap?keys
        value_list = productMap?values
        >
        <#if key_list?has_content>
            <form action="/basket" method="post">
                <@p.pager url page />
                <div class="card-columns text-center p-3">
                    <#list page.content as prod>
                        <#if key_list?seq_contains(prod)>
                            <div class="card">
                                <div class="card-header">
                                    ${prod.productname}
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Short description</p>
                                    <#assign seq_index = key_list?seq_index_of(prod)>
                                    <@pmi.plusMinusInput prod value_list[seq_index]/>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </div>
                <@p.pager url page />

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <script>
                function update(data) {
                    var productId = "#calc" + data.charAt(0);
                    $(productId).val(data.charAt(2));
                }

                function updateProductMap(productId, behav) {
                    $.ajax({
                        url: "/updateProductMap",
                        type: "POST",
                        dataType: "html",
                        data: ({productId: productId, behaviour: behav}),
                        success: update
                    });
                }
            </script>
        <#else>
            There is no product in your cart yet!
        </#if>
    </div>
</@c.page>