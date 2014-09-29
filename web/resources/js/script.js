$(window).scroll(function () {
    if ($(window).scrollTop() >= 600) {
        $('#navigation').css({height: '92px'});
    } else {
        $('#navigation').css({height: '142px'});
    }
});  