# generate API docs for sample-project
for x in sample-project/generated/swagger/*.yaml; do 
	# run redoc-cli against generated sample swagger
	redoc-cli bundle $x;
	# echo the filename, replace .yaml by .html 
	t=$(echo $x | sed 's/\.yaml$/.html/'); 
	# remove everything until the last slash in the html filename
	# test/endpoint/demo.html -> demo.html 
	t=$(echo $t | sed 's@.*/@@'); 
	mv redoc-static.html $t;
done

# generate API docs for swaggen
for x in swaggen/generated/swagger/*.yaml; do 
	# run redoc-cli against generated sample swagger
	redoc-cli bundle $x;
	# echo the filename, replace .yaml by .html 
	t=$(echo $x | sed 's/\.yaml$/.html/'); 
	# remove everything until the last slash in the html filename
	# test/endpoint/demo.html -> demo.html 
	t=$(echo $t | sed 's@.*/@@'); 
	mv redoc-static.html $t;
done