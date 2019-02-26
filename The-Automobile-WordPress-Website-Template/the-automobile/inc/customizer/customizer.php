<?php 

/**
 * The Automobile Theme Customizer.
 *
 * @package The Automobile
 */

//customizer core option
require get_template_directory() . '/inc/customizer/core/customizer-core.php';

//customizer 
require get_template_directory() . '/inc/customizer/core/default.php';
/**
 * Add postMessage support for site title and description for the Theme Customizer.
 *
 * @param WP_Customize_Manager $wp_customize Theme Customizer object.
 */
function the_automobile_customize_register( $wp_customize ) {

	// Load custom controls.
	require get_template_directory() . '/inc/customizer/core/control.php';

	// Load customize sanitize.
	require get_template_directory() . '/inc/customizer/core/sanitize.php';

	// Load customize callback.
	require get_template_directory() . '/inc/customizer/core/callback.php';

	$wp_customize->get_setting( 'blogname' )->transport         = 'postMessage';
	$wp_customize->get_setting( 'blogdescription' )->transport  = 'postMessage';
	
	// Load customize option.
	require get_template_directory() . '/inc/customizer/option-panel.php';
	
	/*theme option panel details*/
	require get_template_directory() . '/inc/customizer/theme-option.php';

	// Register custom section types.
	$wp_customize->register_section_type( 'The_Automobile_Customize_Section_Upsell' );

	// Register sections.
	$wp_customize->add_section(
		new The_Automobile_Customize_Section_Upsell(
			$wp_customize,
			'theme_upsell',
			array(
				'title'    => esc_html__( 'The Automobile Pro', 'the-automobile' ),
				'pro_text' => esc_html__( 'Upgrade To Pro', 'the-automobile' ),
				'pro_url'  => 'http://www.themeinwp.com/theme/the-automobile-pro/',
				'priority'  => 1,
			)
		)
	);

}
add_action( 'customize_register', 'the_automobile_customize_register' );


/**
 * Binds JS handlers to make Theme Customizer preview reload changes asynchronously.
 *
 * @since 1.0.0
 */
function the_automobile_customize_preview_js() {

	wp_enqueue_script( 'the_automobile_customizer', get_template_directory_uri() . '/js/customizer.js', array( 'customize-preview' ), '20130508', true );

}
add_action( 'customize_preview_init', 'the_automobile_customize_preview_js' );

function the_automobile_customizer_css() {
	wp_enqueue_script( 'the_automobile_customize_controls', get_template_directory_uri() . '/assets/twp/js/customizer-admin.js', array( 'customize-controls' ) );
	wp_enqueue_style( 'the_automobile_customize_controls', get_template_directory_uri() . '/assets/twp/css/customizer-controll.css' );
}
add_action( 'customize_controls_enqueue_scripts', 'the_automobile_customizer_css',0 );
