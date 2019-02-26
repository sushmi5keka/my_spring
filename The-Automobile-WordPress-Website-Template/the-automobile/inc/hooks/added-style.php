<?php
/**
 * CSS related hooks.
 *
 * This file contains hook functions which are related to CSS.
 *
 * @package The Automobile
 */

if (!function_exists('the_automobile_trigger_custom_css_action')) :

    /**
     * Do action theme custom CSS.
     *
     * @since 1.0.0
     */
    function the_automobile_trigger_custom_css_action()
    {
        $the_automobile_enable_banner_overlay = the_automobile_get_option('enable_overlay_option');
        ?>
        <style type="text/css">
            <?php
            /* Banner Image */
            if ( $the_automobile_enable_banner_overlay == 1 ){
                ?>
                body .hero-slider.overlay .slide-item .bg-image:before,
                body .inner-header-overlay{
                    filter: alpha(opacity=85);
                    opacity: .85;
                }
            <?php
        } ?>
        </style>

    <?php }

endif;