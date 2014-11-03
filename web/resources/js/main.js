$(function () {
    //SMOOTH SCROLLING
    $('a[href*=#]:not([href=#])').click(function () {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');

            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top
                }, 1000);

                return false;
            }
        }
    });

    //MENU AANPASSEN BIJ SCROLLEN
    $(window).scroll(function () {
        if ($(window).scrollTop() >= 280) {
            $('#menu').addClass("menu-resize");
        } else {
            $('#menu').removeClass("menu-resize");
        }
    });
});