Pod::Spec.new do |s|
  s.name         = "ExampleWidget"
  s.version      = "0.0.1"
  s.summary      = "summary_goes_here"
  s.description  = <<-DESC
                   description_goes_here
                   DESC

  s.homepage     = "http://timeinc.com"
  s.license      = "zlib"
  s.author       = { "name_goes_here" => "email_goes_here" }
  s.platform     = :ios
  ## only specify for non-local sources
  ##s.source     = { :git => 'repo_url_goes_here', :tag => s.version.to_s}  
  s.source_files  = "MFPWidget/Source/*"
  ##s.dependency 'Bond'
  ##s.dependency 'Alamofire' <- For any dependency you have
end
