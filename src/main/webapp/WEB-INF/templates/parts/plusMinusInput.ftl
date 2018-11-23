<#macro plusMinusInput product count>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 text-right"><h4>Quantity:</h4></div>
            <div class="col-lg-6">
                <div class="input-group">
                     <span class="input-group-btn">
                         <button id="minus${product.id}" class="btn btn-primary" type="button"
                                 onclick="updateProductMap(${product.id}, '-')">-</button>
                     </span>
                    <input id="calc${product.id}" type="text" class="form-control" value=${count} disabled>
                    <span class="input-group-btn">
                         <button id="plus${product.id}" class="btn btn-primary" type="button"
                                 onclick="updateProductMap(${product.id}, '+')">+</button>
                     </span>
                </div>
            </div>
        </div>
    </div>
</#macro>