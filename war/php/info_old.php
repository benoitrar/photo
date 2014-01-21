<?php

	header('Content-Type: text/plain;');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("caption_dir", "../content/captions/");
	define("photo_dir", "../content/");

	$q = trim($_GET['q']);
	if ($q) {
		echo "[";
		echo "\n";
		
		$folder = reset(explode(' ', $q));
		if(substr($folder, 0, 7) == "photos/" || substr($folder, 0, 18) == "commissioned_work/") {
		
			$dir = photo_dir.$folder;
			$files = scandir($dir);
			
			if(substr($folder, 0, 7) == "photos/") $subfolder = substr($folder, 7);
			else if(substr($folder, 0, 18) == "commissioned_work/") $subfolder = substr($folder, 18);
			
			$i = 2;
			if($f = fopen(caption_dir.$subfolder.'.txt', "r")) {
				while (false !== ($char = fgetc($f))) {
					if($char != " ") {
						continue;
					}
					
					echo "{\n";
					echo "\t\"name\":\"$files[$i]\",\n";
					$i++;
					echo "\t\"caption\":\"";
					while (false !== ($char = fgetc($f))) {
						if($char == "\n" || $char == "\r") {
							echo "\"\n";
							echo "},\n";
							break;
						} else {
							echo "$char";
						}
					}
				}
				echo "\"\n}";
				fclose($f);
				
				for(; $i<count($files); $i++) {
					echo ",\n{\n\t\"name\":\"$files[$i]\",\n\t\"caption\":\"\"\n}";
				}
			}
			else {
				if($files[2] != "") echo "{\n\t\"name\":\"$files[2]\",\n\t\"caption\":\"\"\n}";
				for($i=3; $i<count($files); $i++) {
					echo ",\n{\n\t\"name\":\"$files[$i]\",\n\t\"caption\":\"\"\n}";
				}
			}
		}
		
		echo "\n";
		echo "]";
	}

?>
