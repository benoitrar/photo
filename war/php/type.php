<?php

	header('Content-Type: text/plain; charset=iso-8859-2');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("main_dir", "../content/");
	define("video", "slideshows/");
	define("image", "photos/");

	$q = trim($_GET['q']);
	if ($q) {
		echo "[";
		echo "\n";
		
		$folder = reset(explode(' ', $q));
		if(is_dir(main_dir.video.$folder)) {
			echo "{ \"name\":\"V\" }";
		}
		else if(is_dir(main_dir.image.$folder)) {
			echo "{ \"name\":\"I\" }";
		}
		
		echo "\n";
		echo "]";
	}

?>
