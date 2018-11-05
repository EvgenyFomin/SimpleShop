<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-3">
            Products Here:<br>
            <div class="list-group">
                <#list prodList as prod>
                    <a href="/products/${prod.productname}"
                       class="list-group-item list-group-item-action <#if productname??><#if productname == prod.productname>active</#if></#if>">${prod.productname}</a>
                </#list>
            </div>
        </div>
        <div class="col-lg-8 ml-3">
            <div class="row">
                Description Here: ${description!''}
            </div>
                <#if known>
                    <div class="row">
                        <label>Leave a comment!</label>
                    </div>
                    <form class="row form-group" action="/products" method="post">
                        <textarea class="form-control" name="message" rows="7"></textarea>
                        <input type="hidden" name="productname" value="${productname}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit">Submit</button>
                    </form>
                <#else>
                    <div class="row">
                        <label>You must log in to leave a comment!</label>
                    </div>
                </#if>
            <#if comments?has_content>
                <div class="card-columns">
                <#list comments as comment>
                    <div class="card">
                        <div class="card-header bg-transparent border-success">${comment.author.username}</div>
                        <div class="card-body text-success">
                            <p class="card-text">${comment.message}</p>
                        </div>
                        <div class="card-footer bg-transparent border-success">Date</div>
                    </div>
                </#list>
                </div>
            </#if>
        </div>
    </div>
</div>
</@c.page>