Dir.mkdir('sample-project_html') unless Dir.exist?('sample-project_html')
Dir.chdir(Dir.pwd+"/sample-project_html")
Dir.glob("../sample-project/**/*.yaml") do |filename|
  system("redoc-cli bundle #{filename}")
  filename = filename.sub(".yaml",".html")
  filename = filename.split("/")[-1]
  File.rename("redoc-static.html", filename)
end

Dir.chdir("..")
Dir.mkdir('swaggen_html') unless Dir.exist?('swaggen_html')
Dir.chdir(Dir.pwd+"/swaggen_html")
Dir.glob("../swaggen/**/*.yaml") do |filename|
  system("redoc-cli bundle #{filename}")
  filename = filename.sub(".yaml",".html")
  filename = filename.split("/")[-1]
  File.rename("redoc-static.html", filename)
end