<?php

	header('Content-Type: text/plain; charset=iso-8859-2');
	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	
	define("main_dir", "../content/");
	
  $images_folders = scandir(main_dir . "photos");
  $images = array_slice($images_folders, 2, count($images_folders)-3);
  
  $slideshows = array_slice(scandir(main_dir . "slideshows"), 2);
  
  $folders = array("I" => $images);
  $folders["V"] = $slideshows;
  
  echo json_encode($folders);
?>