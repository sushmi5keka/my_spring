<?php 

/**
 * Theme Options Panel.
 *
 * @package The Automobile
 */

$default = the_automobile_get_default_theme_options();

// Add Theme Options Panel.
$wp_customize->add_panel( 'theme_front_page_section',
	array(
		'title'      => __( 'Home/Front Page Settings', 'the-automobile' ),
		'priority'   => 200,
		'capability' => 'edit_theme_options',
	)
);

//Top Header section
require get_template_directory() . '/inc/customizer/header-section.php';

	/*slider and its property section*/
	require get_template_directory() . '/inc/customizer/slider.php';