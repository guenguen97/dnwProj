
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

//현재 위치랑 현재 위치 주변 음식점 데이터 요청해주고 받는 함수가 있는거
    (function() {
        // Function to create and append the script
        function loadScript() {
            var script = document.createElement('script');
            script.type = 'text/javascript';
            script.src = '/js/getCurrentPosition.js';
            script.async = true;
            document.head.appendChild(script);
        }

        //먼저 맵 정보랑 현재 위치가 업데이트 된 후에 실행이 되어야해서
        // Call loadScript after a 1-second delay
        setTimeout(loadScript, 1000);
    })();
var g = "asdasd";