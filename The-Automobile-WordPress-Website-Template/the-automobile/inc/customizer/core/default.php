<?php
/**
 * Default theme options.
 *
 * @package The Automobile
 */

if ( ! function_exists( 'the_automobile_get_default_theme_options' ) ) :

	/**
	 * Get default theme options
	 *
	 * @since 1.0.0
	 *
	 * @return array Default theme options.
	 */
	function the_automobile_get_default_theme_options() {

		$defaults = array();

		//headeing section:
		$defaults['top_header_location']				= '';
		$defaults['top_header_telephone']				= '';
		$defaults['top_header_email']					= '';
		
		
		// Slider Section.
		$defaults['show_slider_section']				= 0;
		$defaults['number_of_home_slider']				= 3;
		$defaults['number_of_content_home_slider']		= 30;
		$defaults['select_slider_from']					= 'from-category';
		$defaults['select-page-for-slider']				= 0;
		$defaults['select_category_for_slider']			= 1;
		$defaults['button_text_on_slider']				= esc_html__( 'Browse More', 'the-automobile' );
		

		/*layout*/
		$defaults['home_page_content_status']     	= 1;
		$defaults['enable_overlay_option']			= 1;
		$defaults['enable_moving_animation_option']	= 1;
		$defaults['homepage_layout_option']			= 'full-width';
		$defaults['global_layout']					= 'right-sidebar';
		$defaults['excerpt_length_global']			= 50;
		$defaults['archive_layout']					= 'excerpt-only';
		$defaults['archive_layout_image']			= 'full';
		$defaults['single_post_image_layout']		= 'full';
		$defaults['pagination_type']				= 'default';
		$defaults['copyright_text']					= esc_html__( 'Copyright All right reserved', 'the-automobile' );
		$defaults['social_icon_style']				= 'square';
		$defaults['number_of_footer_widget']		= 2;
		$defaults['breadcrumb_type']				= 'simple';

		// Pass through filter.
		$defaults = apply_filters( 'the_automobile_filter_default_theme_options', $defaults );

		return $defaults;

	}

endif;
