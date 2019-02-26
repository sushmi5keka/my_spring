<?php 

if ( ! function_exists( 'the_automobile_add_breadcrumb' ) ) :

	/**
	 * Add breadcrumb.
	 *
	 * @since 1.0.0
	 */
	function the_automobile_add_breadcrumb() {

		// Bail if Breadcrumb disabled.
		$breadcrumb_type = the_automobile_get_option( 'breadcrumb_type' );
		if ( 'disabled' === $breadcrumb_type ) {
			return;
		}
		// Bail if Home Page.
		if ( is_front_page() || is_home() ) {
			return;
		}
		// Render breadcrumb.
		echo '<div class="col-md-4 mt-20">';
		switch ( $breadcrumb_type ) {
			case 'simple':
				the_automobile_simple_breadcrumb();
			break;

			case 'advanced':
				if ( function_exists( 'bcn_display' ) ) {
					bcn_display();
				}
			break;

			default:
			break;
		}
		echo '</div><!-- .container -->';
		return;

	}

endif;

add_action( 'the_automobile_action_breadcrumb', 'the_automobile_add_breadcrumb' , 10 );
