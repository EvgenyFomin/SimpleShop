<#include "security.ftl">
<#import "forms.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">SIMPLESHOP</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <#if known == false>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Registration</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/add/product">Add Product</a>
                </li>
            </#if>
            <li class="nav-item">
                <a class="nav-link" href="/products">Products</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
            <#if username != "unknown">
                <label><a class="nav-link" href="/basket">Basket</a></label>
            </#if>
            <label class="mr-2">${username}</label>
            <#if username != "unknown">
                <@l.logout />
            </#if>
        </form>
    </div>
</nav>