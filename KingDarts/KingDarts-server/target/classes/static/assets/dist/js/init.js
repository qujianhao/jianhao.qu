/**
 * AdminLTE Demo Menu
 * ------------------
 * You should not use this file in production.
 * This file is for demo purposes only.
 */
$(function () {
    "use strict";

    $("select").each(function () {
        var autoVal = $(this).attr("data-wt-auto");
       if(autoVal != null){
           $(this).val(autoVal);
       }
    });

    //  验证冲突，注释掉
    //$("form").validate({
    //    errorPlacement: function(error, element) {
    //        error.insertBefore( element);
    //    },
    //    errorClass : "validate-error"
    //});

    $("form").tooltip();

    //  重置按钮
    $(".btn-reset").click(function () {
        $(this).parents("form").find("input").each(function () {
            $(this).val("");
        })
        $(this).parents("form").find("select").each(function () {
            $(this).val("");
        })
    });

});
