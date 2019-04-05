# generate API docs for SwaggerGen
for x in swaggen/generated/swagger/*.yaml; do 
	redoc-cli bundle $x;
	t=$(echo $x | sed 's/\.yaml$/.html/'); 
	mv redoc-static.html $t;
done

# generate API docs for sample-project
for x in sample-project/generated/swagger/*.yaml; do 
	redoc-cli bundle $x;
	t=$(echo $x | sed 's/\.yaml$/.html/'); 
	mv redoc-static.html $t;
done