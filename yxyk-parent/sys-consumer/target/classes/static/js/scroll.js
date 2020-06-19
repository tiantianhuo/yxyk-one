$(document).ready(function(e) {
		t = $('.w_left').offset().top;
		mh = $('.w_right').height();
		fh = $('.w_left').height();
		$('.content').scroll(function(e) {
			s = $(this).scrollTop();
			if(s > t) {
				$('.w_left').css('position', 'fixed').css('top','94px').css('width','17.5%');
			} else {
				$('.w_left').css('position', '').css('width', '18%');
			}
		})
	});