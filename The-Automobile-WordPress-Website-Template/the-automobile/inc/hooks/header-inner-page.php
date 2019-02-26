<?php
global $post;
if (!function_exists('the_automobile_single_page_title')) :
    function the_automobile_single_page_title()
    { 
        global $post;
        $global_banner_image = get_header_image();
        // Check if single.
            if (is_singular()) {
                if ( has_post_thumbnail( $post->ID ) ) {
                    $banner_image_single_post = get_post_meta( $post->ID, 'the-automobile-meta-checkbox', true );
                    if ( 'yes' == $banner_image_single_post ) {
                        $banner_image_array = wp_get_attachment_image_src( get_post_thumbnail_id( $post->ID ), 'the-automobile-header-image' );
                        $global_banner_image = $banner_image_array[0];
                    }
                }
            }
            ?>
            <?php 
                $bg_animation = '';
                if (1 != the_automobile_get_option('enable_moving_animation_option')) {
                    $bg_animation = 'no-animation';
                }
            ?>
        <div class="wrapper page-inner-title inner-banner data-bg <?php echo esc_attr($bg_animation); ?>" data-background="<?php echo esc_url($global_banner_image); ?>">
            <header class="entry-header">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">
                            <?php if (is_singular()) { ?>
                                <?php the_title('<h1 class="entry-title">', '</h1>'); ?>
                                <?php if (!is_page()) { ?>
                                    <header class="entry-header">
                                        <div class="entry-meta entry-inner">
                                            <?php
                                            the_automobile_posted_on(); ?>
                                        </div><!-- .entry-meta -->
                                    </header><!-- .entry-header -->
                                <?php }
                            } elseif (is_404()) { ?>
                                <h1 class="entry-title"><?php esc_html_e('Oops! That page can&rsquo;t be found.', 'the-automobile'); ?></h1>
                            <?php } elseif (is_archive()) {
                                the_archive_title('<h1 class="entry-title">', '</h1>');
                                the_archive_description('<div class="taxonomy-description">', '</div>');
                            } elseif (is_search()) { ?>
                                <h1 class="entry-title"><?php printf(esc_html__('Search Results for: %s', 'the-automobile'), '<span>' . get_search_query() . '</span>'); ?></h1>
                            <?php } else { ?>
                                <h1 class="entry-title"><?php esc_html_e('Latest Blog', 'the-automobile'); ?></h1>
                            <?php }
                            ?>
                        </div>
                        <?php
                            /**
                            * Hook - the_automobile_add_breadcrumb.
                            */
                            do_action( 'the_automobile_action_breadcrumb' );
                        ?>
                    </div>
                </div>
            </header><!-- .entry-header -->
            <div class="inner-header-overlay blend-color">

            </div>
        </div>

        <?php
    }
endif;
add_action('the-automobile-page-inner-title', 'the_automobile_single_page_title', 15);
