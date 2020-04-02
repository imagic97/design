const lS = window.localStorage;

module.exports = {
  set(key, value) {
    lS.setItem(key, value);
  },
  get(key) {
    return lS.getItem(key);
  },
  clear() {
    lS.clear();
  },
  delete(key) {
    lS.removeItem(key);
  }
};
