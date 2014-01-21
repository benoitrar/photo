<?php

	header('Content-Type: text/plain; charset=iso-8859-2');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("contact", "../content/contact/contact.txt");
	
	echo "[";
	echo "\n";
	
	if($f = fopen(contact, "r")) {
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
