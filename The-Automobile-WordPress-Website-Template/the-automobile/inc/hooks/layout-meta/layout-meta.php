<?php
/**
 * Implement theme metabox.
 *
 * @package The Automobile
 */

if ( ! function_exists( 'the_automobile_add_theme_meta_box' ) ) :

	/**
	 * Add the Meta Box
	 *
	 * @since 1.0.0
	 */
	function the_automobile_add_theme_meta_box() {

		$apply_metabox_post_types = array( 'post', 'page' );

		foreach ( $apply_metabox_post_types as $key => $type ) {
			add_meta_box(
				'the-automobile-theme-settings',
				esc_html__( 'Single Page/Post Settings', 'the-automobile' ),
				'the_automobile_render_theme_settings_metabox',
				$type
			);
		}

	}

endif;

add_action( 'add_meta_boxes', 'the_automobile_add_theme_meta_box' );

if ( ! function_exists( 'the_automobile_render_theme_settings_metabox' ) ) :

	/**
	 * Render theme settings meta box.
	 *
	 * @since 1.0.0
	 */
	function the_automobile_render_theme_settings_metabox( $post, $metabox ) {

		$post_id = $post->ID;
		$the_automobile_post_meta_value = get_post_meta($post_id);

		// Meta box nonce for verification.
		wp_nonce_field( basename( __FILE__ ), 'the_automobile_meta_box_nonce' );
		// Fetch Options list.
		$page_layout = get_post_meta($post_id,'the-automobile-meta-select-layout',true);
		$page_image_layout = get_post_meta($post_id,'the-automobile-meta-image-layout',true);
		echo $page_image_layout;
	?>
	<div id="the-automobile-settings-metabox-container" class="the-automobile-settings-metabox-container">
		<div id="the-automobile-settings-metabox-tab-layout">
			<h4><?php echo __( 'Layout Settings', 'the-automobile' ); ?></h4>
			<div class="the-automobile-row-content">
				 <!-- Checkbox Field-->
				     <p>
				     <div class="the-automobile-row-content">
				         <label for="the-automobile-meta-checkbox">
				             <input type="checkbox" name="the-automobile-meta-checkbox" id="the-automobile-meta-checkbox" value="yes" <?php if ( isset ( $the_automobile_post_meta_value['the-automobile-meta-checkbox'] ) ) checked( $the_automobile_post_meta_value['the-automobile-meta-checkbox'][0], 'yes' ); ?> />
				             <?php esc_html_e( 'Check To Use Featured Image As Banner Image', 'the-automobile' )?>
				         </label>
				     </div>
				     </p>
			     <!-- Select Field-->
			        <p>
			            <label for="the-automobile-meta-select-layout" class="the-automobile-row-title">
			                <?php esc_html_e( 'Single Page/Post Layout', 'the-automobile' )?>
			            </label>
			            <select name="the-automobile-meta-select-layout" id="the-automobile-meta-select-layout">
				            <option value="left-sidebar" <?php selected('left-sidebar',$page_layout);?>>
				            	<?php esc_html_e( 'Primary Sidebar - Content', 'the-automobile' )?>
				            </option>
				            <option value="right-sidebar" <?php selected('right-sidebar',$page_layout);?>>
				            	<?php esc_html_e( 'Content - Primary Sidebar', 'the-automobile' )?>
				            </option>
				            <option value="no-sidebar" <?php selected('no-sidebar',$page_layout);?>>
				            	<?php esc_html_e( 'No Sidebar', 'the-automobile' )?>
				            </option>
			            </select>
			        </p>

		         <!-- Select Field-->
		            <p>
		                <label for="the-automobile-meta-image-layout" class="the-automobile-row-title">
		                    <?php esc_html_e( 'Single Page/Post Image Layout', 'the-automobile' )?>
		                </label>
                        <select name="the-automobile-meta-image-layout" id="the-automobile-meta-image-layout">
            	            <option value="full" <?php selected('full',$page_image_layout);?>>
            	            	<?php esc_html_e( 'Full', 'the-automobile' )?>
            	            </option>
            	            <option value="left" <?php selected('left',$page_image_layout);?>>
            	            	<?php esc_html_e( 'Left', 'the-automobile' )?>
            	            </option>
            	            <option value="right" <?php selected('right',$page_image_layout);?>>
            	            	<?php esc_html_e( 'Right', 'the-automobile' )?>
            	            </option>
            	            <option value="no-image" <?php selected('no-image',$page_image_layout);?>>
            	            	<?php esc_html_e( 'No Image', 'the-automobile' )?>
            	            </option>
                        </select>
		            </p>
			</div><!-- .the-automobile-row-content -->
		</div><!-- #the-automobile-settings-metabox-tab-layout -->
	</div><!-- #the-automobile-settings-metabox-container -->

    <?php
	}

endif;



if ( ! function_exists( 'the_automobile_save_theme_settings_meta' ) ) :

	/**
	 * Save theme settings meta box value.
	 *
	 * @since 1.0.0
	 *
	 * @param int     $post_id Post ID.
	 * @param WP_Post $post Post object.
	 */
	function the_automobile_save_theme_settings_meta( $post_id, $post ) {

		// Verify nonce.
		if ( ! isset( $_POST['the_automobile_meta_box_nonce'] ) || ! wp_verify_nonce( $_POST['the_automobile_meta_box_nonce'], basename( __FILE__ ) ) ) {
			  return; }

		// Bail if auto save or revision.
		if ( defined( 'DOING_AUTOSAVE' ) || is_int( wp_is_post_revision( $post ) ) || is_int( wp_is_post_autosave( $post ) ) ) {
			return;
		}

		// Check the post being saved == the $post_id to prevent triggering this call for other save_post events.
		if ( empty( $_POST['post_ID'] ) || $_POST['post_ID'] != $post_id ) {
			return;
		}

		// Check permission.
		if ( 'page' === $_POST['post_type'] ) {
			if ( ! current_user_can( 'edit_page', $post_id ) ) {
				return; }
		} else if ( ! current_user_can( 'edit_post', $post_id ) ) {
			return;
		}

		$the_automobile_meta_checkbox =  isset( $_POST[ 'the-automobile-meta-checkbox' ] ) ? esc_attr($_POST[ 'the-automobile-meta-checkbox' ]) : '';
		update_post_meta($post_id, 'the-automobile-meta-checkbox', sanitize_text_field($the_automobile_meta_checkbox));

		$the_automobile_meta_select_layout =  isset( $_POST[ 'the-automobile-meta-select-layout' ] ) ? esc_attr($_POST[ 'the-automobile-meta-select-layout' ]) : '';
		if(!empty($the_automobile_meta_select_layout)){
			update_post_meta($post_id, 'the-automobile-meta-select-layout', sanitize_text_field($the_automobile_meta_select_layout));
		}
		$the_automobile_meta_image_layout =  isset( $_POST[ 'the-automobile-meta-image-layout' ] ) ? esc_attr($_POST[ 'the-automobile-meta-image-layout' ]) : '';
		if(!empty($the_automobile_meta_image_layout)){
			update_post_meta($post_id, 'the-automobile-meta-image-layout', sanitize_text_field($the_automobile_meta_image_layout));
		}
	}

endif;

add_action( 'save_post', 'the_automobile_save_theme_settings_meta', 10, 3 );