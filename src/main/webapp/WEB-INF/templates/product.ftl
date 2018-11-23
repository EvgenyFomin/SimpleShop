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
            <#if images?has_content>
                <div class="row">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel"
                         style="width: 300px; height: 300px">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <#assign i = 1>
                                <#list images as image>
                                    <li data-target="#carouselExampleIndicators" data-slide-to="${i}"></li>
                                    <#assign i += 1>
                                    <#if i == images?size>
                                        <#break>
                                    </#if>
                                </#list>
                        </ol>

                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="/img/Products/${images[0]}">
                            </div>
                            <#assign i = 0>
                            <#list images as image>
                                <#assign i += 1>
                                <#if i == 1>
                                    <#continue>
                                </#if>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="/img/Products/${image}">
                                </div>
                            </#list>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                           data-slide="prev" style="background-color: lightgray">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                           data-slide="next" style="background-color: lightgray">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <div class="ml-3">
                        Description Here: ${currentProduct.description!''}
                    </div>
                </div>

            <div class="row">
                <div class="col-lg-11"></div>
                <form class="col-lg-1" action="/products/addToCart" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-success">Add</button>
                </form>
            </div>
            </#if>

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