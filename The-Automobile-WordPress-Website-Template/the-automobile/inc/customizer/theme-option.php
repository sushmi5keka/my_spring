<?php 

/**
 * Theme Options Panel.
 *
 * @package The Automobile
 */

$default = the_automobile_get_default_theme_options();

// Add Theme Options Panel.
$wp_customize->add_panel( 'theme_option_panel',
	array(
		'title'      => esc_html__( 'Theme Options', 'the-automobile' ),
		'priority'   => 200,
		'capability' => 'edit_theme_options',
	)
);

/*layout management section start */
$wp_customize->add_section( 'theme_option_section_settings',
	array(
		'title'      => esc_html__( 'Layout Management', 'the-automobile' ),
		'priority'   => 100,
		'capability' => 'edit_theme_options',
		'panel'      => 'theme_option_panel',
	)
);

// Setting social_icon_style.
$wp_customize->add_setting( 'social_icon_style',
	array(
	'default'           => $default['social_icon_style'],
	'capability'        => 'edit_theme_options',
	'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'social_icon_style',
	array(
	'label'       => __( 'Social Icon Style', 'the-automobile' ),
	'section'     => 'theme_option_section_settings',
	'type'        => 'select',
	'choices'               => array(
		'square' => __( 'Square', 'the-automobile' ),
		'circle' => __( 'Circle', 'the-automobile' ),
	    ),
	'priority'    => 110,
	)
);

/*Home Page Layout*/
$wp_customize->add_setting( 'home_page_content_status',
	array(
		'default'           => $default['home_page_content_status'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_checkbox',
	)
);
$wp_customize->add_control( 'home_page_content_status',
	array(
		'label'    => esc_html__( 'Enable Static Page Content', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'type'     => 'checkbox',
		'priority' => 150,
	)
);

/*Home Page Layout*/
$wp_customize->add_setting( 'enable_overlay_option',
	array(
		'default'           => $default['enable_overlay_option'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_checkbox',
	)
);
$wp_customize->add_control( 'enable_overlay_option',
	array(
		'label'    => esc_html__( 'Enable Banner Overlay', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'type'     => 'checkbox',
		'priority' => 150,
	)
);


/*Home Page Layout*/
$wp_customize->add_setting( 'enable_moving_animation_option',
	array(
		'default'           => $default['enable_moving_animation_option'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_checkbox',
	)
);
$wp_customize->add_control( 'enable_moving_animation_option',
	array(
		'label'    => esc_html__( 'Enable Banner Moving Animation', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'type'     => 'checkbox',
		'priority' => 150,
	)
);

/*Home Page Layout*/
$wp_customize->add_setting( 'homepage_layout_option',
	array(
		'default'           => $default['homepage_layout_option'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'homepage_layout_option',
	array(
		'label'    => esc_html__( 'Home Page Layout', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'choices'  => array(
                'full-width' => __( 'Full Width', 'the-automobile' ),
                'boxed' => __( 'Boxed', 'the-automobile' ),
		    ),
		'type'     => 'select',
		'priority' => 160,
	)
);

/*Global Layout*/
$wp_customize->add_setting( 'global_layout',
	array(
		'default'           => $default['global_layout'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'global_layout',
	array(
		'label'    => esc_html__( 'Global Layout', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'choices'   => array(
			'left-sidebar'  => esc_html__( 'Primary Sidebar - Content', 'the-automobile' ),
			'right-sidebar' => esc_html__( 'Content - Primary Sidebar', 'the-automobile' ),
			'no-sidebar'    => esc_html__( 'No Sidebar', 'the-automobile' ),
			),
		'type'     => 'select',
		'priority' => 170,
	)
);


/*content excerpt in global*/
$wp_customize->add_setting( 'excerpt_length_global',
	array(
		'default'           => $default['excerpt_length_global'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_positive_integer',
	)
);
$wp_customize->add_control( 'excerpt_length_global',
	array(
		'label'    => esc_html__( 'Set Global Archive Length', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'type'     => 'number',
		'priority' => 175,
		'input_attrs'     => array( 'min' => 1, 'max' => 200, 'style' => 'width: 150px;' ),

	)
);

/*Archive Layout text*/
$wp_customize->add_setting( 'archive_layout',
	array(
		'default'           => $default['archive_layout'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'archive_layout',
	array(
		'label'    => esc_html__( 'Archive Layout', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'choices'               => array(
			'excerpt-only' => __( 'Excerpt Only', 'the-automobile' ),
			'full-post' => __( 'Full Post', 'the-automobile' ),
		    ),
		'type'     => 'select',
		'priority' => 180,
	)
);

/*Archive Layout image*/
$wp_customize->add_setting( 'archive_layout_image',
	array(
		'default'           => $default['archive_layout_image'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'archive_layout_image',
	array(
		'label'    => esc_html__( 'Archive Image Alocation', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'choices'               => array(
			'full' => __( 'Full', 'the-automobile' ),
			'right' => __( 'Right', 'the-automobile' ),
			'left' => __( 'Left', 'the-automobile' ),
			'no-image' => __( 'No image', 'the-automobile' )
		    ),
		'type'     => 'select',
		'priority' => 185,
	)
);

/*single post Layout image*/
$wp_customize->add_setting( 'single_post_image_layout',
	array(
		'default'           => $default['single_post_image_layout'],
		'capability'        => 'edit_theme_options',
		'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'single_post_image_layout',
	array(
		'label'    => esc_html__( 'Single Post/Page Image Alocation', 'the-automobile' ),
		'section'  => 'theme_option_section_settings',
		'choices'               => array(
			'full' => __( 'Full', 'the-automobile' ),
			'right' => __( 'Right', 'the-automobile' ),
			'left' => __( 'Left', 'the-automobile' ),
			'no-image' => __( 'No image', 'the-automobile' )
		    ),
		'type'     => 'select',
		'priority' => 190,
	)
);


// Pagination Section.
$wp_customize->add_section( 'pagination_section',
	array(
	'title'      => __( 'Pagination Options', 'the-automobile' ),
	'priority'   => 110,
	'capability' => 'edit_theme_options',
	'panel'      => 'theme_option_panel',
	)
);

// Setting pagination_type.
$wp_customize->add_setting( 'pagination_type',
	array(
	'default'           => $default['pagination_type'],
	'capability'        => 'edit_theme_options',
	'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'pagination_type',
	array(
	'label'       => __( 'Pagination Type', 'the-automobile' ),
	'section'     => 'pagination_section',
	'type'        => 'select',
	'choices'               => array(
		'default' => __( 'Default (Older / Newer Post)', 'the-automobile' ),
		'numeric' => __( 'Numeric', 'the-automobile' ),
	    ),
	'priority'    => 100,
	)
);



// Footer Section.
$wp_customize->add_section( 'footer_section',
	array(
	'title'      => __( 'Footer Options', 'the-automobile' ),
	'priority'   => 130,
	'capability' => 'edit_theme_options',
	'panel'      => 'theme_option_panel',
	)
);


// Setting social_content_heading.
$wp_customize->add_setting( 'number_of_footer_widget',
	array(
	'default'           => $default['number_of_footer_widget'],
	'capability'        => 'edit_theme_options',
	'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'number_of_footer_widget',
	array(
	'label'    => __( 'Number Of Footer Widget', 'the-automobile' ),
	'section'  => 'footer_section',
	'type'     => 'select',
	'priority' => 100,
	'choices'               => array(
		0 => __( 'Disable footer sidebar area', 'the-automobile' ),
		1 => __( '1', 'the-automobile' ),
		2 => __( '2', 'the-automobile' ),
	    ),
	)
);

// Setting copyright_text.
$wp_customize->add_setting( 'copyright_text',
	array(
	'default'           => $default['copyright_text'],
	'capability'        => 'edit_theme_options',
	'sanitize_callback' => 'sanitize_text_field',
	)
);
$wp_customize->add_control( 'copyright_text',
	array(
	'label'    => __( 'Footer Copyright Text', 'the-automobile' ),
	'section'  => 'footer_section',
	'type'     => 'text',
	'priority' => 120,
	)
);

// Breadcrumb Section.
$wp_customize->add_section( 'breadcrumb_section',
	array(
	'title'      => __( 'Breadcrumb Options', 'the-automobile' ),
	'priority'   => 120,
	'capability' => 'edit_theme_options',
	'panel'      => 'theme_option_panel',
	)
);

// Setting breadcrumb_type.
$wp_customize->add_setting( 'breadcrumb_type',
	array(
	'default'           => $default['breadcrumb_type'],
	'capability'        => 'edit_theme_options',
	'sanitize_callback' => 'the_automobile_sanitize_select',
	)
);
$wp_customize->add_control( 'breadcrumb_type',
	array(
	'label'       => __( 'Breadcrumb Type', 'the-automobile' ),
	'description' => sprintf( __( 'Advanced: Requires %1$sBreadcrumb NavXT%2$s plugin', 'the-automobile' ), '<a href="https://wordpress.org/plugins/breadcrumb-navxt/" target="_blank">','</a>' ),
	'section'     => 'breadcrumb_section',
	'type'        => 'select',
	'choices'               => array(
		'disabled' => __( 'Disabled', 'the-automobile' ),
		'simple' => __( 'Simple', 'the-automobile' ),
		'advanced' => __( 'Advanced', 'the-automobile' ),
	    ),
	'priority'    => 100,
	)
);
