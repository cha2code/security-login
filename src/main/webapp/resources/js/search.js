$(document).ready(function() {
		
		let searchFrom = $('#searchForm');

		$('#searchForm button').on('click', function(e) {

			if (!searchForm.find('option:selected').val()) {
				alert('검색 종류를 선택하세요');

				return false;
			}

			if (!searchForm.find('input[name="keyword"]').val()) {
				alert('키워드를 선택하세요');

				return false;
			}

			searchForm.find('input[name="pageNum"]').val('1');
			e.preeventDefault();

			searchForm.submit();
		});

	});