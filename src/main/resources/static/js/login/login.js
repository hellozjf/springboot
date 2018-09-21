var $yhdm, $mm, $ismd5, $yzm;
$(function () {
    $yhdm = $('#loginmain_vo_yhDm');
    $mm = $('#loginmain_vo_dlmm');
    $yzm = $('#loginmain_vo_yzm');

    $('#bg').css({
        width: $(window).width(),
        height: $(window).height()
    }).hide();

    // 自动选中用户代码输入框
    $('#loginmain_vo_yhDm').focus();

    //选中状态，记住密码
    if ($.cookie('zrar_jzmm') == 'true') {
        $("#loginmain_jzmm").attr('checked', 'checked').parent().addClass('selected');
        $yhdm.val($.cookie('zrar_user'));
        $mm.val($.cookie('zrar_dlmm'));
        $ismd5 = true;
    }

    // 多系统选择 start
    $(".selectitem .input-group").on('click', function (event) {
        $(this).children(".dropbox").toggle();
        if (!$(".dropbox").is(":hidden")) {
            $(this).parent(".selectitem").css("border-color", '#0d8eef');
            $(this).children(".arrow").addClass('droparrow')
        } else {
            $(this).parent(".selectitem").css("border-color", '#ccc');
            $(this).children(".arrow").removeClass('droparrow')
        }
        event.stopPropagation();
    });

    $('.dropbox li').click(function () {
        var txt = $(this).text();
        $(this).parent('.dropbox').siblings(".selecture").text(txt);
    });

    //点击任意部分消失
    $(document).click(function () {
        $(".dropbox").hide();
        $('.selectitem .arrow').removeClass('droparrow')
    });
    // 多系统选择 end

    // 记住用户选中样式切换
    $("#loginmain_jzmm").on('change', function () {
        $(this).parent().toggleClass('selected');
    });


    // 登录事件
    $("#loginbtn").on('click', function () {
        login(this);
    });
});

function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

var publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQZX1Kg0I59W4KwMq0VsZRHp5GE5me474JefjiDK/ewrftAxeKMAnjAfAe2mEb6ZYMz2UKDF2fDKhi3Ctqkqa04HbbuDVu8+fU7A436RUGouyge+MK0hjpLlUKvDL+dknUq4twpArpWjYtXgikQI1nh8XPTNH/nRSy50kQ3ComRwIDAQAB";
var encrypt = new JSEncrypt();
encrypt.setPublicKey(publicKey);

function login() {
    $("#bg").fadeIn();
    var subFlag = {
        "yhdm": false,
        "mm": false
    }, yhdmVal = $yhdm.val(), mmVal = $mm.val();
    if (yhdmVal) {
        subFlag.yhdm = true;
    } else {
        tip("请输入帐号");
        $yhdm.focus();
        $('#bg').fadeOut();
        return false;
    }
    if (mmVal) {
        subFlag.mm = true;
    } else {
        tip("请输入密码");
        $mm.focus();
        $('#bg').fadeOut();
        return false;
    }
    if (subFlag.yhdm && subFlag.mm) {
        mmVal = $ismd5 ? mmVal : encrypt.encrypt(yhdmVal + mmVal + randomString(4));
        _writeUserMm(yhdmVal, mmVal);
        $.ajax({
            url: "${ctx}/usercenter/login/main",
            data: {
                yhDm: yhdmVal,
                mm: mmVal
            },
            type: "POST",
            async: false,
            cache: false,
            success: function (res) {
                var data = $.parseJSON(res);
                if (data.status == "false") {
                    if (data.msg == null) {
                        tip("请输入用户名密码绑定");
                    } else {
                        tip(data.msg);
                    }
                } else {
                    window.location.reload();
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                if (xhr.responseText) {
                    alert($.trim(xhr.responseText));
                } else {
                    tip('服务器正在更新，请稍候重试!');
                }
            },
            complete: function () {
                $("#bg").fadeOut();
            }
        });
    }
    return false;
}