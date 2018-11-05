<#macro login path isRegisterForm>
<div class="container">
    <form action="${path}" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label">Username: </label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}" placeholder="Username">
                <#if usernameError??>
                    <div class="invalid-feedback">
                        <label>${usernameError}</label>
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password">
                <#if passwordError??>
                    <div class="invalid-feedback">
                        <label>${passwordError}</label>
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group">
                <label class="col-sm-2 col-form-label">Confirm password: </label>
                <div class="col-sm-6">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Confirm Password">
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            <label>${password2Error}</label>
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-form-label">Email: </label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                           aria-describedby="emailHelp" placeholder="Email">
                    <#if emailError??>
                        <div class="invalid-feedback">
                            <label>${emailError}</label>
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <div class="form-group ml-3 mt-2">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary ml-2 my-sm-0" type="submit">Sign out</button>
    </form>
</#macro>