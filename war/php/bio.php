<?php

	header('Content-Type: text/plain');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');

	define("bio", "../content/bio/bio.txt");
	
	echo "[";
	echo "\n";
	
	if($f = fopen(bio, "r")) {
		echo "{ \"name\":\"";
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

?>
