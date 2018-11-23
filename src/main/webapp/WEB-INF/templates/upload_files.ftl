<#import "parts/common.ftl" as c>
<@c.page>
<div class="form-group container">
    <div class="custom-file">
        <input type="file" class="custom-file-input" id="customFile" onchange="updateFiles();" multiple>
        <label id="filesQuantity" class="custom-file-label" for="customFile">Choose files...</label>
    </div>
</div>

<script>
    function updateFiles() {
        var oFiles = document.getElementById("customFile").files,
                nFiles = oFiles.length;

        if (nFiles == 0)
            document.getElementById("filesQuantity").innerHTML = "Choose files...";
        else if (nFiles == 1)
            document.getElementById("filesQuantity").innerHTML = oFiles.item(0).name;
        else
            document.getElementById("filesQuantity").innerHTML = nFiles.toString() + " files selected!";
    }
</script>
</@c.page>