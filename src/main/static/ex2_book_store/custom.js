let qE = function(str) {
	return document.querySelector(str);
};

let addRel = function(dom, action, method) {
	if(typeof dom === "string") dom = qE(dom);
	dom.addEventListener(action, method);
};

let getParamFromUrl = function(paramName) {
	let urL = location.href;
	let params = urL.slice(urL.indexOf("?") + 1);
	params = params.split("&");
	for(let [name, value] of params.map(x => x.split("="))) {
		if(name === paramName) return value;
	}
};

function elt(name, attrs, ...children) { 
	// ...操作符，把[a,b,c]打散为a,b,c，打散调用children时仍然要...
	let dom = document.createElement(name);
	for(let attr of Object.keys(attrs)) {
		dom.setAttribute(attr, attrs[attr]);
	}
	for(let child of children) {
		if(["string", "number"].indexOf(typeof child) == -1) {
			dom.appendChild(child);
		} else {
			dom.appendChild(document.createTextNode(child));
		}
	}
	return dom;
}

let tokenKeeper = {
	keepTemporarily: function(token) {
		sessionStorage.setItem("token", token);
	},
	keepForever: function(token) {
		localStorage.setItem("token", token);
	},
	removeToken: function() {
		localStorage.removeItem("token");
		sessionStorage.removeItem("token");
	},
	getToken: function() {
		let tk = sessionStorage.getItem("token");
		if(tk == null) tk = localStorage.getItem("token");
		return tk;
	},
};

let wrapAjax = function(settings) {
	return new Promise((resolve, reject) => {
		$.ajax(settings).done(result => {
			resolve(result);
		}).fail(e => {
			reject(e.responseJSON);
		});;
	});
};

let rpc = {
	getToken: function(username, pwd) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/tokens?username=${username}&pwd=${pwd}`,
			"method": "GET",
		}
		return wrapAjax(settings);
	},
	getUser: function(token) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": "http://localhost:8080/test/api/v1/users",
			"method": "GET",
			"headers": {
				"Authorization": `Bearer ${token}`,
			},
		}
		return wrapAjax(settings);
	},
	listItem: function(offset, limit) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/items?offset=${offset}&limit=${limit}`,
			"method": "GET",
		}
		return wrapAjax(settings);
	},
	listCartItem: function(uid, token) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/cart_items?uid=${uid}`,
			"method": "GET",
			"headers": {
				"Authorization": `Bearer ${token}`,
			},
		}
		return wrapAjax(settings);
	},
	addCartItem: function(uid, itemId, num, token) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/cart_items?uid=${uid}&itemId=${itemId}&num=${num}`,
			"method": "POST",
			"headers": {
				"Authorization": `Bearer ${token}`,
			},
		}
		return wrapAjax(settings);
	},
	removeCartItem: function(uid, itemId, num, token) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/cart_items?uid=${uid}&itemId=${itemId}&num=${num}`,
			"method": "DELETE",
			"headers": {
				"Authorization": `Bearer ${token}`,
			},
		}
		return wrapAjax(settings);
	},
	getCartBill: function(uid, token) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/cart_bill?uid=${uid}`,
			"method": "GET",
			"headers": {
				"Authorization": `Bearer ${token}`,
			},
		}
		return wrapAjax(settings);
	},
};