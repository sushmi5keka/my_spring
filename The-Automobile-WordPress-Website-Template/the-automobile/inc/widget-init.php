<?php
/**
 * Register widget area.
 *
 * @link https://developer.wordpress.org/themes/functionality/sidebars/#registering-a-sidebar
 */
function the_automobile_widgets_init() {
	register_sidebar( array(
		'name'          => esc_html__( 'Sidebar', 'the-automobile' ),
		'id'            => 'sidebar-1',
		'description'   => esc_html__( 'Add widgets here.', 'the-automobile' ),
		'before_widget' => '<section id="%1$s" class="widget %2$s">',
		'after_widget'  => '</section>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => esc_html__( 'Home Page Main Widget', 'the-automobile' ),
		'id'            => 'home-page-mian',
		'description'   => esc_html__( 'Add widgets here.', 'the-automobile' ),
		'before_widget' => '<section id="%1$s" class="widget %2$s">',
		'after_widget'  => '</section>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );
	$the_automobile_footer_widgets_number = the_automobile_get_option('number_of_footer_widget');

	if( $the_automobile_footer_widgets_number > 0 ){
	    register_sidebar(array(
	        'name' => __('Footer Column One', 'the-automobile'),
	        'id' => 'footer-col-one',
	        'description' => __('Displays items on footer section.','the-automobile'),
	        'before_widget' => '<aside id="%1$s" class="widget %2$s">',
	        'after_widget' => '</aside>',
	        'before_title'  => '<h1 class="widget-title white-textcolor">',
	        'after_title'   => '</h1>',
	    ));
	    if( $the_automobile_footer_widgets_number > 1 ){
	        register_sidebar(array(
	            'name' => __('Footer Column Two', 'the-automobile'),
	            'id' => 'footer-col-two',
	            'description' => __('Displays items on footer section.','the-automobile'),
	            'before_widget' => '<aside id="%1$s" class="widget %2$s">',
	            'after_widget' => '</aside>',
	            'before_title'  => '<h1 class="widget-title white-textcolor">',
	            'after_title'   => '</h1>',
	        ));
	    }
	}
}
add_action( 'widgets_init', 'the_automobile_widgets_init' );
