<#macro plusMinusInput product count>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="input-group">
                     <span class="input-group-btn">
                         <button id="minus${product.id}" class="btn btn-primary" type="button">-</button>
                     </span>
                    <input id="calc${product.id}" type="text" class="form-control" name="${product.productname}"
                           value=${count}>
                    <span class="input-group-btn">
                         <button id="plus${product.id}" class="btn btn-primary" type="button">+</button>
                     </span>
                </div>
            </div>
        </div>
    </div>
    <script>
        $('#minus${product.id}').click(function () {
            $("#calc${product.id}").val(parseInt($("#calc${product.id}").val()) - 1);
        });
        $('#plus${product.id}').click(function () {
            $("#calc${product.id}").val(parseInt($("#calc${product.id}").val()) + 1);
        });
    </script>
</#macro>