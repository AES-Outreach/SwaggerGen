# generate API docs for SwaggerGen
cd swaggen/generated/swagger
for x in *.yaml; do 
	redoc-cli bundle $x;
	t=$(echo $x | sed 's/\.yaml$/.html/'); 
	if [ ! -d "../html" ]
	then
		mkdir ../html
	fi
	mv redoc-static.html ../html/$t;
done

# go back to project root
cd ../../../

# generate API docs for sample-project
cd sample-project/generated/swagger
for x in *.yaml; do 
	redoc-cli bundle $x;
	t=$(echo $x | sed 's/\.yaml$/.html/'); 
	if [ ! -d "../html" ]
	then
		mkdir ../html
	fi
	mv redoc-static.html ../html/$t;
done