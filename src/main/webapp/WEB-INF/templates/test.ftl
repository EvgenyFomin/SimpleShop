<#import "parts/common.ftl" as c>
<@c.page>
<div class="container p-3">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel"
         style="width: 300px; height: 200px; margin: 0 auto">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <#--<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>-->
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active" style="width: 300px; height: 200px">
                <img class="d-block w-100" src="/img/static/image/Products/Ccc/apple.jpg" alt="First slide"
                     style="width: 300px; height: 200px">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="/resources/static/image/dota22.jpg" alt="Second slide">
            </div>
        <#--<div class="carousel-item">-->
        <#--<img class="d-block w-100" src=".../800x400?auto=yes&bg=555&fg=333&text=Third slide" alt="Third slide">-->
        <#--</div>-->
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev"
           style="background-color: lightgray">
            <span class="carousel-control-prev-icon" aria-hidden="false"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next"
           style="background-color: lightgray">
            <span class="carousel-control-next-icon" aria-hidden="false"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

</@c.page>