!function (e) {
    "use strict";
    var n = window.TWP_JS || {};
    n.stickyMenu = function () {
        e(window).scrollTop() > 350 ? e("#masthead").addClass("nav-affix") : e("#masthead").removeClass("nav-affix")
    },
        n.mobileMenu = {
            init: function () {
                this.toggleMenu(), this.addClassParent(), this.addRemoveClasses()
            },
            toggleMenu: function () {
                var n = this,
                    t = e("#masthead");
                e("#nav-toggle").click(function (a) {
                    a.preventDefault(), t.hasClass("open") ? (t.removeClass("open"), e(".menu").find("li").removeClass("show")) : (t.addClass("open"), n.showSubmenu())
                })
            },
            addClassParent: function () {
                e(".menu").find("li > ul").each(function () {
                    e(this).parent().addClass("parent")
                })
            },
            addRemoveClasses: function () {
                var n = e(".menu");
                e(window).width() < 992 ? n.addClass("mobile") : (e("body").removeClass("open"), n.removeClass("mobile").find("li").removeClass("show"))
            },
            showSubmenu: function () {
                e(".menu").find("a").each(function () {
                    var n = e(this);
                    n.next("ul").length && n.one("click", function (n) {
                        n.preventDefault(), e(this).parent().addClass("show")
                    })
                })
            }
        },

        n.TwpSearch = function () {
            e(".search-button").click(function () {
                e(".search-box").slideToggle("500");
            });

            e('.search-button').click(function () {
                e(this).toggleClass('active');
            });

        },

        n.DataBackground = function () {
            e('.bg-image').each(function () {
                var src = e(this).children('img').attr('src');
                e(this).css('background-image', 'url(' + src + ')').children('img').hide();
            });
        },

        n.InnerBanner = function () {
            var pageSection = e(".data-bg");
            pageSection.each(function (indx) {
                if (e(this).attr("data-background")) {
                    e(this).css("background-image", "url(" + e(this).data("background") + ")");
                }
            });
        },

        n.TwpHeroSlider = function () {
            var ecarousel = e('.carousel');
            if (ecarousel.length) {
                ecarousel.each(function () {
                    var items, singleItem, autoPlay, transition, drag, stopOnHover, navigation, pagination;
                    items = e(this).data('items');
                    singleItem = e(this).data('single-item') === undefined ? false : true;
                    autoPlay = e(this).data('autoplay');
                    transition = e(this).data('transition') === undefined ? false : e(this).data('transition');
                    pagination = e(this).data('pagination') === undefined ? false : true;
                    navigation = e(this).data('navigation') === undefined ? false : true;
                    drag = transition == "fade" ? false : true;
                    stopOnHover = transition === "fade" || pagination === false || navigation === false ? false : true;
                    e(this).owlCarousel({
                        items: 1,
                        singleItem: singleItem,
                        autoPlay: autoPlay,
                        pagination: pagination,
                        navigation: navigation,
                        smartSpeed: 500,
                        stopOnHover: 1,
                        autoplayHoverPause: true,
                        transitionStyle: transition,
                        mouseDrag: drag,
                        touchDrag: drag,
                        lazyLoad: true,
                        nav: true,
                        navText: false,
                        loop: (e('.carousel').children().length) == 1 ? false : true,
                        navRewind: false,
                        autoplay: true,
                        autoplayTimeout: 8000
                    });
                });
            }
        },
        n.TwpSlider = function () {
            e(".blog-carousel").owlCarousel({
                loop: true,
                margin: 20,
                nav: false,
                smartSpeed: 500,
                autoplay: 5000,
                navText: [''],
                responsive: {
                    0: {
                        items: 1
                    },
                    768: {
                        items: 2
                    },
                    990: {
                        items: 3
                    }
                }
            });
            var setMinHeight = function(minheight = 0) {
                jQuery('.blog-carousel').each(function(i,e){
                    var oldminheight = minheight;
                    jQuery(e).find('.owl-item').each(function(i,e){
                        minheight = jQuery(e).height() > minheight ? jQuery(e).height() : minheight;
                    });
                    jQuery(e).find('.owl-item').css("min-height",minheight + "px");
                    minheight = oldminheight;
                });
            };

            setMinHeight();
        },

        // SHOW/HIDE SCROLL UP //
        n.show_hide_scroll_top = function () {
            if (e(window).scrollTop() > e(window).height() / 2) {
                e("#scroll-up").fadeIn(300);
            } else {
                e("#scroll-up").fadeOut(300);
            }
        },

        // SCROLL UP //
        n.scroll_up = function () {
            e("#scroll-up").on("click", function () {
                e("html, body").animate({
                    scrollTop: 0
                }, 800);
                return false;
            });

        },

        n.twp_animate = function () {
            if (e('.wow').length) {
                var wow = new WOW(
                    {
                        boxClass: 'wow',
                        animateClass: 'animated',
                        offset: 0,
                        mobile: true,
                        live: true
                    }
                );
                wow.init();
            }
        },

        n.twp_preloader = function () {
            e(window).load(function(){
                e("body").addClass("page-loaded");
            });
        },

        n.twp_matchheight = function () {
            e(function() {
                e('.blog-item').matchHeight();
            });
        },

        n.mobile_up = function () {
            function myFunction(x) {
                if (x.matches) { // If media query matches
                    e("#top-nav").removeClass("in");
                } else {
                    e("#top-nav").addClass("in");
                }
            }

            var x = window.matchMedia("(max-width: 991px)")
            myFunction(x) // Call listener function at run time
            x.addListener(myFunction) // Attach listener function on state changes
        },

        e(document).ready(function () {
            n.mobileMenu.init(), n.TwpSearch(), n.DataBackground(), n.InnerBanner(), n.TwpHeroSlider(), n.TwpSlider(), n.scroll_up(), n.twp_animate(), n.twp_preloader(), n.twp_matchheight(), n.mobile_up();
        }), e(window).scroll(function () {
        n.stickyMenu(), n.show_hide_scroll_top();
    }), e(window).resize(function () {
        n.mobileMenu.addRemoveClasses();
    })
}(jQuery);

