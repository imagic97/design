const sl = window.sessionStorage;

module.exports = {
  set(key, value) {
    sl.setItem(key, value);
  },
  get(key) {
    return sl.getItem(key);
  },
  clear() {
    sl.clear();
  }
};
