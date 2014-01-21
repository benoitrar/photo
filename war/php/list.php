<?php

	header('Content-Type: text/plain; charset=iso-8859-2');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("main_dir", "../content/");

	$q = trim($_GET['q']);
	if ($q) {
		$folder = reset(explode(' ', $q));
		if(substr($folder, 0, 7) == "photos/" || substr($folder, 0, 18) == "commissioned_work/") {
			echo "[";
			echo "\n";
			
			$dir = main_dir.$folder;
			$files = scandir($dir);
			
			if($files[2] != "") echo "{ \"name\":\"$files[2]\" }";
			for($i=3; $i<count($files); $i++) {
				echo ",\n{ \"name\":\"$files[$i]\" }";
			}
			
			echo "\n";
			echo "]";
		}
		else {
			echo $folder;
		}
	}

?>
