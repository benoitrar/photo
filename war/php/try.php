<?php

	header('Content-Type: text/plain; charset=iso-8859-2');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("main_dir", "../content/");
	
	function retrieveDirnamesSortedWithType($dir, $type) {
		return addNameAndTypeAttribute(
			   deleteFirstThreeCharactersFromStrings(
			   listDirectoryAlphabetically($dir), $type));
	}
	
	function listDirectoryAlphabetically($dir) {
		$dh = opendir($dir);
		while (false !== ($filename = readdir($dh))) {
			if ($filename != "." && $filename != ".." && $filename != "welcome") {
				$dirs[] = $filename;
			}
		}
		sort($dirs);
		return $dirs;
	}
	
	function deleteFirstThreeCharactersFromStrings($string_array) {
		foreach ($string_array as $string) {
			$new_array[] = substr($string, 0);//3);
		}
		return $new_array;
	}
	
	function addNameAndTypeAttribute($string_array, $type) {
		$count = count(string_array);
		if ($count >= 0) {
			$new_array[] =  "{\n\t\"name\":\"$string_array[0]\",\n\t\"type\":$type\n}";
			for ($i=1; $i<=$count; $i++) {
				$new_array[] =  ",\n{\n\t\"name\":\"$string_array[$i]\",\n\t\"type\":$type\n}";
			}
		}
		return $new_array;
	}
	
	function printArr($arr) {
		foreach ($arr as $element) {
			echo $element;
		}
	}
	
	$photo_dir = main_dir . "photos";
	$photo_dirs = retrieveDirnamesSortedWithType($photo_dir,"I");
	
	$video_dir = main_dir . "slideshows";
	$video_dirs = retrieveDirnamesSortedWithType($video_dir,"V");

	printArr($photo_dirs);

	print_r($video_dirs);

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