<?php

	header('Content-Type: text/plain');
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
				$array[] = $entry;
			}
		}
		if ($count >= 0) {
			echo "{\n\t\"name\":\"$array[$count]\",\n\t\"type\":\"I\"\n}";
			for ($i=$count-1; $i>=0; $i--) {
				echo ",\n{\n\t\"name\":\"$array[$i]\",\n\t\"type\":\"I\"\n}";
			}
		}
		
		closedir($handle);
	}
	//echo "\n";
	echo "]";
?>