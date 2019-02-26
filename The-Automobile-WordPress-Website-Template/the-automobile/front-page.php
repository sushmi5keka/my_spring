<?php
/**
 * The template for displaying home page.
 * @package The Automobile
 */

get_header();
if ( 'posts' == get_option( 'show_on_front' ) ) {
    include( get_home_template() );
    }
else{
	/**
	 * the_automobile_action_front_page hook
	 * @since The Automobile 0.0.2
	 *
	 * @hooked the_automobile_action_front_page -  10
	 * @sub_hooked the_automobile_action_front_page -  10
	 */
	do_action( 'the_automobile_action_front_page' );
		
	/*main widget for footer section*/	
	if( is_active_sidebar( 'home-page-mian' ) ){
 		dynamic_sidebar( 'home-page-mian' ); 
	}
		if (the_automobile_get_option('home_page_content_status') == 1) {
			?>
			<section class="section-block recent-blog">
					<div id="primary" class="content-area">
					<?php
					while ( have_posts() ) : the_post();
						the_title('<h2 class="entry-title section-title">', '</h2>');
						get_template_part( 'template-parts/content', 'page' );

					endwhile; // End of the loop.
					?>
					</div><!-- #primary -->
				    <?php get_sidebar(); ?>
				    <!-- #sidebar -->
			</section>
		<?php }
}
get_footer();