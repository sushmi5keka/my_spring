<?php 

/**
 * Theme Options Panel.
 *
 * @package The Automobile
 */

$default = the_automobile_get_default_theme_options();


// our header Main Section.
$wp_customize->add_section( 'header_section_settings',
	array(
		'title'      => esc_html__( 'Top Heading Section', 'the-automobile' ),
		'priority'   => 100,
		'capability' => 'edit_theme_options',
		'panel'      => 'theme_front_page_section',
	)
);

// Setting - top_header_location.
$wp_customize->add_setting( 'top_header_location',
	array(
		'default'           => $default['top_header_location'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'sanitize_text_field',
	)
);
$wp_customize->add_control( 'top_header_location',
	array(
		'label'    => esc_html__( 'Location', 'the-automobile' ),
		'section'  => 'header_section_settings',
		'type'     => 'text',
		'priority' => 100,

	)
);

// Setting - top_header_telephone.
$wp_customize->add_setting( 'top_header_telephone',
	array(
		'default'           => $default['top_header_telephone'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'sanitize_text_field',
	)
);
$wp_customize->add_control( 'top_header_telephone',
	array(
		'label'    => esc_html__( 'Contact Number', 'the-automobile' ),
		'section'  => 'header_section_settings',
		'type'     => 'text',
		'priority' => 110,

	)
);

// Setting - top_header_email.
$wp_customize->add_setting( 'top_header_email',
	array(
		'default'           => $default['top_header_email'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'sanitize_email',
	)
);
$wp_customize->add_control( 'top_header_email',
	array(
		'label'    => esc_html__( 'Email Address', 'the-automobile' ),
		'section'  => 'header_section_settings',
		'type'     => 'text',
		'priority' => 120,

	)
);