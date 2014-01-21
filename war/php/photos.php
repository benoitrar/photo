<?php

	header('Content-Type: text/plain; charset=iso-8859-2');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("main_dir", "../content/");

	echo "[";
	//echo "\n";
	
	if ($handle = opendir(main_dir . "photos")) {
		$count = -1;
		while (false !== ($entry = readdir($handle))) {
			if ($entry != "." && $entry != ".." && $entry != "welcome") {
				$count++;
				$images[] = $entry;
			}
		}
		if ($count >= 0) {
			echo "{\n\t\"name\":\"$images[$count]\",\n\t\"type\":\"I\"\n}";
			for ($i=$count-1; $i>=0; $i--) {
				echo ",\n{\n\t\"name\":\"$images[$i]\",\n\t\"type\":\"I\"\n}";
			}
		}
		
		closedir($handle);
	}
	if ($handle = opendir(main_dir . "slideshows")) {
		$count = -1;
		while (false !== ($entry = readdir($handle))) {
			if ($entry != "." && $entry != "..") {
				$count++;
				$slideshows[] = $entry;
			}
		}
		//if ($count >= 0) {
			//echo "{\n\t\"name\":\"$slideshows[$count]\",\n\t\"type\":\"V\"\n}";
			for ($i=$count; $i>=0; $i--) {
				echo ",\n{\n\t\"name\":\"$slideshows[$i]\",\n\t\"type\":\"V\"\n}";
			}
		//}
		
		closedir($handle);
	}
	//echo "\n";
	echo "]";
?>