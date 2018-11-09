<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
        <div class="container p-3">
            <@p.pager url page />
            <div class="card-columns text-center p-3">
                <#list page.content as prod>
                    <div class="card">
                        <div class="card-header">
                            ${prod.productname}
                        </div>
                        <div class="card-body">
                            <p class="card-text">Short description</p>
                            <a href="/products/${prod.productname}" class="btn btn-primary">Full description</a>
                        </div>
                    </div>
                </#list>
            </div>
            <@p.pager url page />
        </div>
</@c.page>