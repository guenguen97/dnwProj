  var mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 10
    };

 var map = new naver.maps.Map('map', mapOptions);


      var HOME_PATH = window.HOME_PATH || '.';

  var cityhall = new naver.maps.LatLng(37.5666805, 126.9784147),
      map = new naver.maps.Map('map', {
          center: cityhall.destinationPoint(0, 500),
          zoom: 15
      }),
      marker = new naver.maps.Marker({
          map: map,
          position: cityhall
      });

  var contentString = [
          '<div class="iw_inner">',
          '   <h3>서울특별시청</h3>',
          '   <p>서울특별시 중구 태평로1가 31 | 서울특별시 중구 세종대로 110 서울특별시청<br />',
          '       <img src="'+ HOME_PATH +'/img/example/hi-seoul.jpg" width="55" height="55" alt="서울시청" class="thumb" /><br />',
          '       02-120 | 공공,사회기관 &gt; 특별,광역시청<br />',
          '       <a href="http://www.seoul.go.kr" target="_blank">www.seoul.go.kr/</a>',
          '   </p>',
          '</div>'
      ].join('');

  var infowindow = new naver.maps.InfoWindow({
      content: contentString
  });

  naver.maps.Event.addListener(marker, "click", function(e) {
      if (infowindow.getMap()) {
          infowindow.close();
      } else {
          infowindow.open(map, marker);
      }
  });

  infowindow.open(map, marker);


  <!-- 네이버 에도 위치 마커 추가 시켜보기 -->


       var mapOptions2 = {
          center: new naver.maps.LatLng(37.3595704, 127.105399),
          zoom: 10
      };

      var map2 = new naver.maps.Map('map', mapOptions2);




      var cityhall2 = new naver.maps.LatLng(37.3595704, 127.105399),
                      map2 = new naver.maps.Map('map', {
                          center: cityhall2.destinationPoint(0, 500),
                          zoom: 15
                      }),
                          <!--     아래 마커는 실제 마커가 생성되게 해주는거 -->
                      marker2 = new naver.maps.Marker({
                          map: map2,
                          position: cityhall2
                      });

      var contentString2 = [
          '<div class="iw_inner">',
          '   <h3>네이버 본사</h3>',
          '   <p>서울특별시 중구 태평로1가 31 | 서울특별시 중구 세종대로 110 서울특별시청<br />',
          '       <img src="'+ HOME_PATH +'/img/example/hi-seoul.jpg" width="55" height="55" alt="서울시청" class="thumb" /><br />',
          '       02-120 | 공공,사회기관 &gt; 특별,광역시청<br />',
          '       <a href="http://www.seoul.go.kr" target="_blank">www.seoul.go.kr/</a>',
          '   </p>',
          '</div>'
      ].join('');

  <!--이거 만드는 즉시 정보창이 생성됨-->
  var infowindow2 = new naver.maps.InfoWindow({
      content: contentString2
  });

  <!--    맨처음 마커 클릭 하지않아도 정보창 뜨게 해주는 코드 -->
      infowindow2.open(map2, marker2);

      naver.maps.Event.addListener(marker2, "click", function(e) {
      if (infowindow2.getMap()) {
          infowindow2.close();
      } else {
          infowindow2.open(map2, marker2);
      }
  });

      var HOME_PATH = window.HOME_PATH || '.';