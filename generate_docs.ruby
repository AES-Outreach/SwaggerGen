Dir.glob("swaggen/**/*.yaml") do |filename|
  system("redoc-cli bundle #{filename}")
  filename = filename.sub(".yaml",".html")
  filename = filename.split("/")[-1]
  File.rename("redoc-static.html", filename)
end