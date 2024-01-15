
(function() {
    // Fetch the API key from the server
    fetch('/api/get_naver_map_key')
        .then(response => response.json())
        .then(data => {
            // Load the Naver Map API dynamically with the fetched key
            var script = document.createElement('script');
            script.type = 'text/javascript';
            script.src = 'https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=' + data.apiKey;
            script.async = true;
            document.head.appendChild(script);
            console.log(data.apiKey);

                 (function() {
                                                                            var script = document.createElement('script');
                                                                            script.type = 'text/javascript';
                                                                            script.src = '/js/map_option.js';
                                                                            script.async = true;
                                                                            document.head.appendChild(script);
                                                                        })();






//                             (function() {
//                                            var script = document.createElement('script');
//                                            script.type = 'text/javascript';
//                                            script.src = '/js/many_infor_test.js';
//                                            script.async = true;
//                                            document.head.appendChild(script);
//                                        })();

        })
        .catch(error => console.error('Error fetching API key:', error));
})();


     (function() {
                            var script = document.createElement('script');
                            script.type = 'text/javascript';
                            script.src = '/js/getCurrentPosition.js';
                            script.async = true;
                            document.head.appendChild(script);
                        })();
var g = "asdasd";