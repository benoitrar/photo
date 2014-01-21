<?php

	header('Content-Type: text/plain');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("main_dir", "../content/photos/");

	$q = trim($_GET['q']);
	if ($q) {
		$folder = reset(explode(' ', $q));
		
		echo "[";
		echo "\n";
		
		if ($handle = opendir(main_dir . $folder)) {
			$count = -1;
			while (false !== ($entry = readdir($handle))) {
				if ($entry != "." && $entry != "..") {
					$count++;
					$array[] = $entry;
				}
			}
			if ($count >= 0) {
				echo "{ \"name\":\"$array[$count]\" }";
				for ($i=$count-1; $i>=0; $i--) {
					echo ",\n{ \"name\":\"$array[$i]\" }";
				}
			}
			
			closedir($handle);
		}
		echo "\n";
		echo "]";
	}

?>
