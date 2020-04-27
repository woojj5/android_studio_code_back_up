이번 12월 말과 1월 말까지, 앱은 어떠한 형식으로 구성이 되어있고, 어떠한 방식으로 구현되는지 
그에 대한 기초를 다지는 일에 중점을 두었고, 그 기초에 관해서 배운 후 그 방식들을 이용한
프로토 타입 APP을 구현해 보았습니다.

카카오 버스 앱과 비슷한 형식의 앱을 구현을 해보려고 노력했습니다
<img src = "https://user-images.githubusercontent.com/34640735/73611659-557ac700-4627-11ea-9bae-5e9a0bb7e62d.png"> </img>

이를 위해서는 우선 버스 위치에 대한 API가 필요하고 이는 
https://www.data.go.kr/ 
여기서 찾아볼수 있습니다

<img src = "https://user-images.githubusercontent.com/34640735/73611666-63c8e300-4627-11ea-929f-ff0257a82a5b.png"> </img>

여기서 제공되는 Open API를 파싱해서, 필요한 데이터들을 가져오는 작업을 시행, 이를 위해서는 기권에서 발급해준 key 값과 입력값에 맞는 api 또한 찾아야 한다 
또한 이러한 파싱을 하기 위해서는 AndroidManifest.xml에 코드를 추가해 줘야 합니다

<img src = "https://user-images.githubusercontent.com/34640735/73611838-75ab8580-4629-11ea-90c2-562061c31967.png"> </img>


<img src = "https://user-images.githubusercontent.com/34640735/73611686-9ecb1680-4627-11ea-8790-642a14b12a1a.png"> </img>

<img src = "https://user-images.githubusercontent.com/34640735/73611671-6d524b00-4627-11ea-8666-dee786864758.png"> </img>

<img src = "https://user-images.githubusercontent.com/34640735/73611688-a5598e00-4627-11ea-8fba-c5910113d9c9.png"> </img>

doit 안드로이드 프로그래밍에서 배운 예제를 활용해서 프래그먼트와 리사이클러 뷰를 이용해서 파싱을 하는 방식을 구현

<img src = "https://user-images.githubusercontent.com/34640735/73611696-b1dde680-4627-11ea-897d-570f54521a5a.png"> </img>

여기까지 개발하고 깨달은 점

1)공공 데이터중에서 노선도를 구현한 데이터가 없다. 

2)공공 데이터의 버스 도착 예정 시각과 카카오 어플의 버스 도착 예정 시간의 차이가 크다
