<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<div class="container-fluid mt-3">
    <div class="row">
        <div class="col-lg-3">
            Products Here:<br>
            <div class="list-group">
                <#list prodList as prod>
                    <a href="/products/${prod.productname}"
                       class="list-group-item list-group-item-action <#if currentProduct.productname??><#if currentProduct.productname == prod.productname>active</#if></#if>">${prod.productname}</a>
                </#list>
            </div>
        </div>
        <div class="col-lg-8 ml-3">
            <div class="row">
                Description Here: ${currentProduct.description!''}
            </div>
            <div class="row">
                <div class="col-lg-11"></div>
                <form class="col-lg-1" action="/products/addToCart" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-success">Add</button>
                </form>
            </div>
                <#if known>
                    <div class="row mt-3">
                        <label>Leave a comment!</label>
                    </div>
                    <form class="form-group row" action="/products" method="post">
                        <textarea class="form-control ${(messageError??)?string('is-invalid', '')}" name="message"
                                  rows="7"></textarea>
                        <#if messageError??>
                            <div class="invalid-feedback">
                                <label>${messageError}</label>
                            </div>
                        </#if>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary mt-3">Submit</button>
                    </form>
                <#else>
                    <div class="row">
                        <label>You must log in to leave a comment!</label>
                    </div>
                </#if>
            <#if currentProduct.comments?has_content>
                <div class="card-columns">
                <#list currentProduct.comments as comment>
                    <div class="card">
                        <div class="card-header bg-transparent border-success">${comment.author.username}</div>
                        <div class="card-body text-success">
                            <p class="card-text">${comment.message}</p>
                        </div>
                        <div class="card-footer bg-transparent border-success">${comment.date!'None'}</div>
                    </div>
                </#list>
                </div>
            </#if>
        </div>
    </div>
</div>
</@c.page>