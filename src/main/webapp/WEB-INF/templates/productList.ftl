<#import "parts/common.ftl" as c>
<@c.page>
    <div class="container">
        <div class="card-columns text-center p-3">
            <#list prodList as prod>
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
    </div>
</@c.page>