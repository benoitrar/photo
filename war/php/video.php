<?php

	header('Content-Type: text/plain; charset=iso-8859-2');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("main_dir", "../content/slideshows/");

	$q = trim($_GET['q']);
	if ($q) {
		$folder = reset(explode(' ', $q));
		
		echo "[";
		echo "\n";
		
		$dir = main_dir.$folder."/";
		
		//echo $dir.$folder.".txt";
		if($f = fopen($dir.$folder.".txt", "r")) {
			echo "{ \"name\":\"";
			while (false !== ($char = fgetc($f))) {
				if($char != "\n" && $char != "\r") {
					echo "$char";
				} else {
					break;
				}
			}
			echo "\" }";
			
			echo ",\n{ \"name\":\"";
			while (false !== ($char = fgetc($f))) {
				if($char == "\n" || $char == "\r") {
					echo "<br>";
				} else {
					echo "$char";
				}
			}
			echo "\" }";
			fclose($f);
		}
		
		echo "\n";
		echo "]";
	}

?>
