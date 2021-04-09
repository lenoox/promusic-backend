INSERT INTO public.brands (brand_id,brand_name) VALUES (1,'TSPV') ON CONFLICT DO NOTHING;
INSERT INTO public.brands (brand_id,brand_name) VALUES (2,'DHQForMusic') ON CONFLICT DO NOTHING;
INSERT INTO public.brands (brand_id,brand_name) VALUES (3,'PRTU-X') ON CONFLICT DO NOTHING;
INSERT INTO public.brands (brand_id,brand_name) VALUES (4,'YourHighSoundXK') ON CONFLICT DO NOTHING;
SELECT setval('brands_brand_id_seq', 5);
INSERT INTO public.order_status (order_status_id, status_name) VALUES (1,'Nowy') ON CONFLICT DO NOTHING;
INSERT INTO public.order_status (order_status_id, status_name) VALUES (2,'Opłacony') ON CONFLICT DO NOTHING;
INSERT INTO public.order_status (order_status_id, status_name) VALUES (3,'Wysłany') ON CONFLICT DO NOTHING;
SELECT setval('order_status_order_status_id_seq', 4);