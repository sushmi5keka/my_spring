<?php
/**
 * Collection of other function file.
 */
require get_template_directory() . '/inc/common-functions-hooks.php';

/*widgets init*/
require get_template_directory() . '/inc/widgets/widgets.php';

/*widget init*/
require get_template_directory() . '/inc/widget-init.php';

/*layout meta*/
require get_template_directory() . '/inc/hooks/layout-meta/layout-meta.php';

/*header css*/
require get_template_directory() . '/inc/hooks/added-style.php';

/*section hook init*/
require get_template_directory() . '/inc/hooks/breadcrumb.php';
require get_template_directory() . '/inc/hooks/header-inner-page.php';
require get_template_directory() . '/inc/hooks/slider.php';
