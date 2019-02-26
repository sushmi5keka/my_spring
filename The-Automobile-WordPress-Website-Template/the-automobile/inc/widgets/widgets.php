<?php
/**
 * Theme widgets.
 *
 * @package The Automobile
 */

// Load widget base.
require_once get_template_directory() . '/inc/widgets/widget-base-class.php';

if (!function_exists('the_automobile_load_widgets')) :
    /**
     * Load widgets.
     *
     * @since 1.0.0
     */
    function the_automobile_load_widgets()
    {
        // The_Automobile_Intro_section widget.
        register_widget('The_Automobile_Intro_section');

        // The_Automobile_Blog_Widget widget.
        register_widget('The_Automobile_Blog_Widget');
        
        // The_Automobile_Featured_widget.
        register_widget('The_Automobile_Featured_widget');

        // The_Automobile_Callback.
        register_widget('The_Automobile_Callback');

        // The_Automobile_Testimonial.
        register_widget('The_Automobile_Testimonial');

    }
endif;
add_action('widgets_init', 'the_automobile_load_widgets');

/*the automobile intro section*/
if (!class_exists('The_Automobile_Intro_section')) :

    /**
     * The Automobile Widget
     *
     * @since 1.0.0
     */
    class The_Automobile_Intro_section extends The_Automobile_Widget_Base
    {

        /**
         * Sets up a new widget instance.
         *
         * @since 1.0.0
         */
        function __construct()
        {
            $opts = array(
                'classname' => 'the_automobile_intro_widget',
                'description' => __('Displays the content on the basis of page selected', 'the-automobile'),
                'customize_selective_refresh' => true,
            );
            $fields = array(
                'title' => array(
                    'label' => __('Title:', 'the-automobile'),
                    'type'  => 'text',
                    'class' => 'widefat',
                ),
                'use_page_title' => array(
                    'label'   => __( 'Use Main Page Title as Widget Title', 'the-automobile' ),
                    'type'    => 'checkbox',
                    'default' => true,
                    ),
                'featured_page' => array(
                    'label'            => __( 'Select Main Page:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'intro_page_icon_1' => array(
                    'label' => __('Icon For Page (http://ionicons.com/)1:', 'the-automobile'),
                    'type'  => 'text',
                    'class' => 'widefat',
                    'placeholder' =>'ion-ios-chatboxes-outline',
                ),
                'intro_page_1' => array(
                    'label'            => __( 'Select Page 1:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'intro_page_icon_2' => array(
                    'label' => __('Icon For Page 2:', 'the-automobile'),
                    'type'  => 'text',
                    'placeholder' =>'ion-ios-chatboxes-outline',
                    'class' => 'widefat',
                ),
                'intro_page_2' => array(
                    'label'            => __( 'Select Page 2:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'intro_page_icon_3' => array(
                    'label' => __('Icon For Page 3:', 'the-automobile'),
                    'type'  => 'text',
                    'placeholder' =>'ion-ios-chatboxes-outline',
                    'class' => 'widefat',
                ),
                'intro_page_3' => array(
                    'label'            => __( 'Select Page 3:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
            );

            parent::__construct('the-automobile-intro-widget', __('Automobile About Widget', 'the-automobile'), $opts, array(), $fields);
        }

        /**
         * Outputs the content for the current widget instance.
         *
         * @since 1.0.0
         *
         * @param array $args Display arguments.
         * @param array $instance Settings for the current widget instance.
         */
        function widget($args, $instance)
        {
            $params = $this->get_params($instance);
            echo $args['before_widget'];
            // ID validation.
            $intro_main_page_id = '';
            $the_automobile_intro_page_title = '';
            $the_automobile_main_content  = '';
            $the_automobile_permalink  = '';
            $the_automobile_main_image  = '';
            if ( absint( $params['featured_page'] ) > 0 ) {
                $intro_main_page_id = absint( $params['featured_page'] );
            }
            if ( absint( $intro_main_page_id ) > 0 ) {
                $qargs = array(
                    'p'             => absint( $intro_main_page_id ),
                    'post_type'     => 'any',
                    'no_found_rows' => true,
                );

                $the_query = new WP_Query( $qargs );
                if ( $the_query->have_posts() ) {
                    while ( $the_query->have_posts() ) {
                        $the_query->the_post();

                        if ( true === $params['use_page_title'] ) {
                            $the_automobile_intro_page_title = esc_html(get_the_title());
                        }
                        else {
                            if ( ! empty( $params['title'] ) ) {
                                $the_automobile_intro_page_title =  $params['title'];
                            }
                        }
                        if(has_post_thumbnail()){
                            $thumb = wp_get_attachment_image_src( get_post_thumbnail_id( get_the_ID() ), 'the-automobile-700-465' );
                            $the_automobile_main_image = $thumb['0'];
                        }
                        else{
                            $the_automobile_main_image = get_template_directory_uri().'/images/no-image-medium.jpg';
                        }
                        $the_automobile_permalink = esc_url(get_permalink());
                        $word_count_intro = 70;
                        $the_automobile_main_content = the_automobile_words_count(absint($word_count_intro), get_the_content());
                        ?>
                        <?php
                    }

                    wp_reset_postdata();
                }
            ?>
                            <!-- about Section -->
            <div class="about-section about-upper pt-50 pb-50">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
                            <div class="section-title-block text-left section-title-block-2 mb-20 pb-40">
                                <h2 class="section-title">
                                    <?php echo esc_html($the_automobile_intro_page_title ); ?>
                                </h2>
                            </div>
                            <p>
                               <?php echo wp_kses_post($the_automobile_main_content); ?>
                            </p>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 mt-70">
                            <figure class="image-box wow fadeInRight" data-wow-duration="1s">
                                <a href="<?php echo esc_url($the_automobile_permalink); ?>" class="twp-figframe">
                                    <img src="<?php echo esc_url($the_automobile_main_image); ?>">
                                    <div class="image-box-hover">
                                        <div class="hover-wrapper">
                                            <span class="ion-paper-airplane alt-textcolor"></span>
                                        </div>
                                    </div>
                                </a>
                            </figure>
                        </div>

                        <div class="list-item-wrapper clearfix">
                            <?php
                            // ID validation.
                            $intro_sub_page_id = '';
                            $data_delay = 0.5;
                            for ($i=1; $i <= 3 ; $i++) {
                                if ( absint( $params['intro_page_'.$i] ) > 0 ) {
                                    $intro_sub_page_id = absint( $params['intro_page_'.$i] );
                                }
                                if ( absint( $intro_sub_page_id ) > 0 ) {
                                    $qargs = array(
                                        'p'             => absint( $intro_sub_page_id ),
                                        'post_type'     => 'any',
                                        'no_found_rows' => true,
                                    );
                                    $the_query = new WP_Query( $qargs );
                                    if ( $the_query->have_posts() ) {
                                        while ( $the_query->have_posts() ) {
                                            $the_query->the_post();
                                            ?>
                                                <div class="item col-sm-4 mb-20 mt-20 wow fadeIn" data-wow-delay="<?php echo esc_attr($data_delay); ?>s">
                                                    <div class="item-iconbox section-bg-1" data-mh="col-group-about">
                                                        <div class="icon-image">
                                                            <span class='<?php echo esc_attr( $params['intro_page_icon_'.$i] ); ?> secondary-textcolor'></span>
                                                            <h3 class="block-title mt-10 mb-20"><?php the_title(); ?></h3>
                                                        </div>

                                                        <div class="content">
                                                            <p><?php 
                                                            $word_count_intros = 25;
                                                            $the_automobile_sub_content = the_automobile_words_count(absint($word_count_intros), get_the_content());
                                                            echo esc_html($the_automobile_sub_content); ?></p>
                                                            <a href="<?php the_permalink();?>" class="learn-more"><?php _e('Learn More','the-automobile' ); ?><i class="ion-ios-arrow-thin-right"></i> </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            <?php
                                        }
                                        $data_delay = $data_delay + 0.5;
                                        $intro_sub_page_id = '';
                                        wp_reset_postdata();
                                    }
                                }
                            } ?>
                            </div>

                    </div>
                </div>
            </div>
            <!-- made main page of intro section compulsory -->
            <?php } ?>
            <?php echo $args['after_widget'];
        }
    }
endif;

/*The_Automobile_Blog_Widget widget*/
if (!class_exists('The_Automobile_Blog_Widget')) :

    /**
     * The Automobile Widget
     *
     * @since 1.0.0
     */
    class The_Automobile_Blog_Widget extends The_Automobile_Widget_Base
    {

        /**
         * Sets up a new widget instance.
         *
         * @since 1.0.0
         */
        function __construct()
        {
            $opts = array(
                'classname' => 'the_automobile_blog_widgets',
                'description' => __('Displays post form selected category As Blog Post', 'the-automobile'),
                'customize_selective_refresh' => true,
            );
            $fields = array(
                'title' => array(
                    'label' => __('Title:', 'the-automobile'),
                    'type' => 'text',
                    'class' => 'widefat',
                ),
                'short_discription' => array(
                    'label' => __('Short Discription:', 'the-automobile'),
                    'type'  => 'text',
                    'class' => 'widget-content widefat'
                ),
                'post_category' => array(
                    'label' => __('Select Category:', 'the-automobile'),
                    'type' => 'dropdown-taxonomies',
                    'show_option_all' => __('All Categories', 'the-automobile'),
                ),
                'post_number' => array(
                    'label' => __('Number of Posts:', 'the-automobile'),
                    'type' => 'number',
                    'default' => 4,
                    'css' => 'max-width:60px;',
                    'min' => 1,
                    'max' => 8,
                ),
                'excerpt_length' => array(
                    'label' => __('Excerpt Length:', 'the-automobile'),
                    'description' => __('Number of words', 'the-automobile'),
                    'type' => 'number',
                    'css' => 'max-width:60px;',
                    'default' => 20,
                    'min' => 0,
                    'max' => 200,
                ),
            );

            parent::__construct('the-automobile-blog-layout', __('Automobile Blog Widget', 'the-automobile'), $opts, array(), $fields);
        }

        /**
         * Outputs the content for the current widget instance.
         *
         * @since 1.0.0
         *
         * @param array $args Display arguments.
         * @param array $instance Settings for the current widget instance.
         */
        function widget($args, $instance)
        {

            $params = $this->get_params($instance);

            echo $args['before_widget'];

            $qargs = array(
                'posts_per_page' => esc_attr($params['post_number']),
                'no_found_rows' => true,
            );
            if (absint($params['post_category']) > 0) {
                $qargs['category'] = absint($params['post_category']);
            }
            global $post;
            $all_posts = get_posts($qargs);
            ?>
            <?php if (!empty($all_posts)) : ?>
            <div class="blog-section pt-50 pb-50">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="text-center section-title-block section-title-block-1 mb-30 pb-40">
                                <?php if (!empty($params['title'])) {
                                    echo '<h2 class="section-title  wow fadeIn">'.esc_html($params['title']).'</h2>';
                                }?>
                                <?php if ( ! empty( $params['short_discription'] ) ) { ?>
                                    <p class="wow fadeInUp" data-wow-delay=".7s"><?php echo wp_kses_post( $params['short_discription']); ?></p>
                                <?php } ?>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="blog-carousel">
                                <?php foreach ($all_posts as $key => $post) : ?>
                                    <?php setup_postdata($post); ?>
                                    <div class="blog-item section-bg-1">
                                        <div class="inner-box wow fadeIn" data-wow-delay="300ms" data-wow-duration="1500ms">
                                            <figure class="image-box">
                                                <?php if (has_post_thumbnail()) {
                                                    $thumb = wp_get_attachment_image_src( get_post_thumbnail_id( get_the_ID() ), 'the-automobile-700-465' );
                                                    $url = $thumb['0'];
                                                    } else {
                                                        $url = get_template_directory_uri() . '/images/no-image-medium.jpg';
                                                }
                                                ?>
                                                <a href="<?php the_permalink(); ?>">
                                                    <img src="<?php echo esc_url($url); ?>" alt="<?php the_title_attribute(); ?>">
                                                    <div class="image-box-hover">
                                                        <div class="hover-wrapper">
                                                            <span class="ion-paper-airplane alt-textcolor"></span>
                                                        </div>
                                                    </div>
                                                </a>
                                            </figure>
                                            <div class="block-content">
                                                <h3 class="block-title"><?php the_title(); ?></h3>
                                                <div class="title-seperator secondary-bgcolor wow fadeInRight" data-wow-delay=".7s"></div>
                                                <div class="block-detail mb-20">
                                                    <?php if (absint($params['excerpt_length']) > 0) : ?>
                                                        <?php
                                                        $excerpt = the_automobile_words_count(absint($params['excerpt_length']), get_the_content());
                                                        echo wp_kses_post(wpautop($excerpt));
                                                        ?>
                                                    <?php endif; ?>
                                                </div>
                                                <a href="<?php the_permalink(); ?>" class="btn btn-block twp-btn twp-btn-secondary"><?php esc_html_e('Learn More', 'the-automobile'); ?><span
                                                        class="icon flaticon-arrows-1"></span></a>
                                            </div>
                                        </div>
                                    </div>
                                <?php endforeach; ?>
                                <?php wp_reset_postdata(); ?>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <?php endif; ?>
            <?php echo $args['after_widget'];
        }
    }
endif;


/*The_Automobile_Featured_widget widget*/
if (!class_exists('The_Automobile_Featured_widget')) :

    /**
     * The Automobile Widget
     *
     * @since 1.0.0
     */
    class The_Automobile_Featured_widget extends The_Automobile_Widget_Base
    {

        /**
         * Sets up a new widget instance.
         *
         * @since 1.0.0
         */
        function __construct()
        {
            $opts = array(
                'classname' => 'the_automobile_featured_widget',
                'description' => __('Displays post form selected pages as featured page', 'the-automobile'),
                'customize_selective_refresh' => true,
            );
            $fields = array(
                'title' => array(
                    'label' => __('Title:', 'the-automobile'),
                    'type'  => 'text',
                    'class' => 'widefat',
                ),
                'featured_page_1' => array(
                    'label'            => __( 'Select Page 1:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'featured_page_2' => array(
                    'label'            => __( 'Select Page 2:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'featured_page_3' => array(
                    'label'            => __( 'Select Page 3:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
            );

            parent::__construct('the-automobile-featured-layout', __('Automobile Featured Widget', 'the-automobile'), $opts, array(), $fields);
        }

        /**
         * Outputs the content for the current widget instance.
         *
         * @since 1.0.0
         *
         * @param array $args Display arguments.
         * @param array $instance Settings for the current widget instance.
         */
        function widget($args, $instance)
        {

            $params = $this->get_params($instance);

            echo $args['before_widget'];
            ?>
            <div class="about-section about-lower section-bg-1 pt-80 pb-40">
                <div class="container">
                    <div class="text-center section-title-block section-title-block-1 mb-30 pb-40">
                        <?php if (!empty($params['title'])) {
                            echo '<h2 class="section-title  wow fadeIn">'.esc_html($params['title']).'</h2>';
                        } ?>
                    </div>
                    <div class="row pt-20">

                            <?php 
                            // ID validation.
                            $featured_page_ids = '';
                            for ($i=1; $i <= 3 ; $i++) { 
                                if ( absint( $params['featured_page_'.$i] ) > 0 ) {
                                    $featured_page_ids = absint( $params['featured_page_'.$i] );
                                }
                                if ( absint( $featured_page_ids ) > 0 ) {
                                    $qargs = array(
                                        'p'             => absint( $featured_page_ids ),
                                        'post_type'     => 'any',
                                        'no_found_rows' => true,
                                    );

                                    $the_query = new WP_Query( $qargs );
                                    if ( $the_query->have_posts() ) {
                                        while ( $the_query->have_posts() ) {
                                            $the_query->the_post();
                                            ?>
                                                <div class="col-sm-4 mb-20">
                                                    <div class="about-us-box light-bgcolor">
                                                        <figure class="image-box">
                                                            <a href="<?php the_permalink(); ?>">
                                                            <?php if(has_post_thumbnail()){
                                                                $thumb = wp_get_attachment_image_src( get_post_thumbnail_id( get_the_ID() ), 'the-automobile-700-465' );
                                                                $image_url = $thumb['0'];
                                                            }
                                                            else{
                                                                $image_url = get_template_directory_uri().'/images/no-image-medium.jpg';
                                                            } ?>
                                                                <img src="<?php echo esc_url($image_url); ?>">
                                                                <div class="image-box-hover">
                                                                    <div class="hover-wrapper">
                                                                        <span class="ion-paper-airplane alt-textcolor"></span>
                                                                    </div>
                                                                </div>
                                                            </a>
                                                        </figure>
                                                        <div class="image-box-detail">
                                                            <h3 class="block-title">
                                                                <?php the_title(); ?>
                                                            </h3>
                                                            <p class="mb-10">
                                                                <?php
                                                                $word_count_featured = 25;
                                                                $the_automobile_feature_content = the_automobile_words_count(absint($word_count_featured), get_the_content());
                                                                echo esc_html($the_automobile_feature_content);
                                                                ?>
                                                            </p>
                                                            <a href="<?php the_permalink(); ?>" class="learn-more twp-btn twp-btn-secondary no-radius"> <?php _e('Learn More','the-automobile'); ?></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            <?php
                                        }
                                        $featured_page_ids = '';
                                        wp_reset_postdata();
                                    }
                                }
                            } ?>
                    </div>
                </div>
            </div>
            <?php echo $args['after_widget'];
        }
    }
endif;


/*The_Automobile_Testimonial widget*/
if (!class_exists('The_Automobile_Testimonial')) :

    /**
     * The Automobile Widget
     *
     * @since 1.0.0
     */
    class The_Automobile_Testimonial extends The_Automobile_Widget_Base
    {

        /**
         * Sets up a new widget instance.
         *
         * @since 1.0.0
         */
        function __construct()
        {
            $opts = array(
                'classname' => 'the_automobile_testimonial_widget',
                'description' => __('Displays post form selected pages as featured page', 'the-automobile'),
                'customize_selective_refresh' => true,
            );
            $fields = array(
                'title' => array(
                    'label' => __('Title:', 'the-automobile'),
                    'type'  => 'text',
                    'class' => 'widefat',
                ),
                'image_url' => array(
                    'label' => __('Background Image:', 'the-automobile'),
                    'type'  => 'image',
                ),
                'testimonial_page_1' => array(
                    'label'            => __( 'Select Page 1:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'testimonial_page_2' => array(
                    'label'            => __( 'Select Page 2:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'testimonial_page_3' => array(
                    'label'            => __( 'Select Page 3:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
                'testimonial_page_4' => array(
                    'label'            => __( 'Select Page 4:', 'the-automobile' ),
                    'type'             => 'dropdown-pages',
                    'class'            => 'widefat',
                    'show_option_none' => __( '&mdash; Select &mdash;', 'the-automobile' ),
                ),
            );

            parent::__construct('the-automobile-testimonial-widget', __('Automobile Testimonial Widget', 'the-automobile'), $opts, array(), $fields);
        }

        /**
         * Outputs the content for the current widget instance.
         *
         * @since 1.0.0
         *
         * @param array $args Display arguments.
         * @param array $instance Settings for the current widget instance.
         */
        function widget($args, $instance)
        {

            $params = $this->get_params($instance);

            echo $args['before_widget'];
            if (! empty( $params['image_url'] )) {
                $image_url = esc_url( $params['image_url'] );
            } else{
                $image_url = get_template_directory_uri() . '/images/cta-bg.jpg';
            }
            ?>
            <div class="testmonial-section section-bg-1 section-cta section-block data-bg" data-background="<?php echo esc_url($image_url); ?>">
                <div class="cta-overlay blend-color"></div>
                <div class="container">
                    <div class="row col-pad-0">
                        <div class="testmonial-wrapper pt-50 clearfix">
                            <div class="col-sm-12">
                                <div class="text-center section-title-block section-title-block-1 mb-30 pb-40">
                                    <?php if (!empty($params['title'])) {
                                        echo '<h2 class="section-title wow fadeIn">'.esc_html($params['title']).'</h2>';
                                    } ?>
                                </div>
                            </div>
                                <?php 
                                // ID validation.
                                $testimonial_page_ids = '';
                                for ($i=1; $i <= 4 ; $i++) { 
                                    if ( absint( $params['testimonial_page_'.$i] ) > 0 ) {
                                        $testimonial_page_ids = absint( $params['testimonial_page_'.$i] );
                                    }
                                    if ( absint( $testimonial_page_ids ) > 0 ) {
                                        $qargs = array(
                                            'p'             => absint( $testimonial_page_ids ),
                                            'post_type'     => 'any',
                                            'no_found_rows' => true,
                                        );

                                        $the_query = new WP_Query( $qargs );
                                        if ( $the_query->have_posts() ) {
                                            while ( $the_query->have_posts() ) {
                                                $the_query->the_post();
                                                if ($i <= 2) {
                                                    $auto_class_testimonial = 'col-half-left';
                                                } else{
                                                    $auto_class_testimonial = 'col-half-right';
                                                }
                                                if (($i == 1) || ($i == 4)) {
                                                    $auto_class_bg = 'secondary-bgcolor';
                                                } elseif (($i == 2) || ($i == 3)) {
                                                    $auto_class_bg = 'primary-bgcolor';
                                                }
                                                ?>
                                                    <div class="col-md-6 col-sm-12 col-xs-12" data-mh="col-group">
                                                        <div class="cta-overlay <?php echo esc_attr($auto_class_bg); ?>"></div>
                                                        <div class="col-half <?php echo esc_attr( $auto_class_testimonial); ?>">
                                                            <figure class="image-box">
                                                                <a href="<?php the_permalink(); ?>">
                                                                    <?php if(has_post_thumbnail()){
                                                                        $thumb = wp_get_attachment_image_src( get_post_thumbnail_id( get_the_ID() ), 'the-automobile-400-460' );
                                                                        $image_urls = $thumb['0'];
                                                                    }
                                                                    else{
                                                                        $image_urls = get_template_directory_uri().'/images/no-image.jpg';
                                                                    } ?>
                                                                    <img src="<?php echo esc_url($image_urls); ?>">
                                                                    <div class="image-box-hover">
                                                                        <div class="hover-wrapper">
                                                                            <span class="ion-paper-airplane alt-textcolor"></span>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </figure>
                                                        </div>
                                                        <div class="col-half">
                                                            <div class="testmonial-box-detail">
                                                                    <h3 class="block-title">
                                                                        <a href="<?php the_permalink();?>"><?php the_title(); ?></a>
                                                                    </h3>
                                                                <div class="title-seperator alt-bgcolor wow fadeIn" data-wow-delay=".5s"></div>
                                                                    <p>
                                                                        <?php
                                                                            $word_count_testimonial = 30;
                                                                            $the_automobile_testimonial_content = the_automobile_words_count(absint($word_count_testimonial), get_the_content());
                                                                            echo esc_html($the_automobile_testimonial_content);
                                                                        ?>
                                                                    </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                <?php
                                            }
                                            $testimonial_page_ids = '';
                                            wp_reset_postdata();
                                        }
                                    }
                                } ?>
                        </div>
                    </div>
                </div>
            </div>
            <?php echo $args['after_widget'];
        }
    }
endif;



/*The_Automobile_Callback widget*/
if (!class_exists('The_Automobile_Callback')) :

    /**
     * The Automobile Widget
     *
     * @since 1.0.0
     */
    class The_Automobile_Callback extends The_Automobile_Widget_Base
    {

        /**
         * Sets up a new widget instance.
         *
         * @since 1.0.0
         */
        function __construct()
        {
            $opts = array(
                'classname' => 'the_automobile_callback_widget',
                'description' => __('Displays callback section on the basis of information listed here', 'the-automobile'),
                'customize_selective_refresh' => true,
            );
            $fields = array(
                'title' => array(
                    'label' => __('Title:', 'the-automobile'),
                    'type' => 'text',
                    'class' => 'widefat',
                ),
                'discription' => array(
                    'label' => __('Discription:', 'the-automobile'),
                    'type'  => 'text',
                    'class' => 'widget-content widefat'
                ),
                'image_urls' => array(
                    'label' => __('Background Image:', 'the-automobile'),
                    'type'  => 'image',
                ),
                'button_text' => array(
                    'label' => __('Button Text:', 'the-automobile'),
                    'type' => 'text',
                    'class' => 'widefat',
                ),
                'button_url' => array(
                   'label' => __('Button URL:', 'the-automobile'),
                   'type' => 'url',
                   'class' => 'widefat',
                    ),
            );

            parent::__construct('the-automobile-callback-widget', __('Automobile Call to Action Widget', 'the-automobile'), $opts, array(), $fields);
        }

        /**
         * Outputs the content for the current widget instance.
         *
         * @since 1.0.0
         *
         * @param array $args Display arguments.
         * @param array $instance Settings for the current widget instance.
         */
        function widget($args, $instance)
        {

            $params = $this->get_params($instance);

            echo $args['before_widget'];
            if (! empty( $params['image_urls'] )) {
                $image_url = esc_url( $params['image_urls'] );
            } else{
                $image_url = get_template_directory_uri() . '/images/cta-bg.jpg';
            }
            ?>
            <div class="section-cta section-block text-center data-bg" data-background="<?php echo esc_url( $params['image_urls'] ) ?>">
                <div class="cta-overlay blend-color-1"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2">
                            <div class="cta-title mt-40 mb-30">
                                    <?php if ( ! empty( $params['title'] ) ) { ?>
                                        <div class="text-center section-title-block section-title-block-1 mb-30 pb-40">
                                            <h2 class="section-title white-textcolor wow fadeIn" data-wow-duration="0.5s">
                                                <?php echo esc_html($params['title']); ?>
                                            </h2>
                                        </div>

                                    <?php } ?>
                                <p class="wow fadeInLeft" data-wow-duration="1s"><?php echo wp_kses_post( $params['discription']); ?></p>
                                <?php if (( ! empty( $params['button_url'] ) ) || ( ! empty( $params['button_text'] ) )) { ?>
                                <div class="cta-btns-group wow fadeInRight" data-wow-duration="1.5s">
                                    <a href="<?php echo esc_url($params['button_url']); ?>" class="btn twp-btn twp-btn-primary"><?php echo esc_html($params['button_text'] );?></a>
                                </div>
                                <?php } ?>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <?php echo $args['after_widget'];
        }
    }
endif;


